package com.tay.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tay.common.constants.MqConstants;
import com.tay.userservice.dao.SysUserDao;
import com.tay.userservice.dto.EsQueryParam;
import com.tay.userservice.dto.SysUserDTO;
import com.tay.userservice.entity.SysUserEntity;
import com.tay.userservice.service.SysUserService;
import com.tay.userservice.utils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author tay
 * @since 1.0.0 2023-05-29
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private SysUserDao sysUserDao;
    @Autowired
    private RestHighLevelClient client;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SysUserDTO sysUserDTO) {
        SysUserEntity sysUserEntity = ConvertUtils.sourceToTarget(sysUserDTO, SysUserEntity.class);
        sysUserDao.insert(sysUserEntity);
        // 同时推送到到mq
        rabbitTemplate.convertAndSend(MqConstants.ACCOUNT_EXCHANGE, MqConstants.ACCOUNT_INSERT_KEY,sysUserEntity.getId());
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        sysUserDao.deleteById(id);
        rabbitTemplate.convertAndSend(MqConstants.ACCOUNT_EXCHANGE, MqConstants.ACCOUNT_DELETE_KEY,id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(SysUserDTO sysUserDTO) {
        SysUserEntity sysUserEntity = ConvertUtils.sourceToTarget(sysUserDTO, SysUserEntity.class);
        sysUserDao.updateById(sysUserEntity);
        rabbitTemplate.convertAndSend(MqConstants.ACCOUNT_EXCHANGE, MqConstants.ACCOUNT_INSERT_KEY,sysUserEntity.getId());
    }

    @Override
    public List<SysUserDTO> list(SysUserDTO sysUserDTO) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(sysUserDTO.getUsername())) {
            queryWrapper.eq("username", sysUserDTO.getUsername());
        }
        if (StringUtils.isNotEmpty(sysUserDTO.getRealName())) {
            queryWrapper.like("real_name", sysUserDTO.getRealName());
        }
        if (StringUtils.isNotEmpty(sysUserDTO.getEmail())) {
            queryWrapper.like("email", sysUserDTO.getEmail());
        }
        return ConvertUtils.sourceToTarget(sysUserDao.selectList(queryWrapper),SysUserDTO.class);
    }
    @Override
    public IPage<SysUserDTO> page(SysUserDTO sysUserDTO) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(sysUserDTO.getUsername()),"username", sysUserDTO.getUsername())
                .like(StringUtils.isNotEmpty(sysUserDTO.getRealName()), "real_name", sysUserDTO.getRealName())
                .like(StringUtils.isNotEmpty(sysUserDTO.getRealName()), "email", sysUserDTO.getEmail());
        //分页参数
        long curPage = 1;
        long limit = 10;

        if(sysUserDTO.getPage() != null){
            curPage = sysUserDTO.getPage();
        }
        if(sysUserDTO.getPage() != null){
            limit = sysUserDTO.getLimit();
        }
        Page<SysUserEntity> page = Page.of(curPage,limit);
        sysUserDao.selectPage(page, queryWrapper);
        return page.convert(sysUserEntity -> ConvertUtils.sourceToTarget(sysUserEntity, SysUserDTO.class));
    }

    @Override
    public SysUserDTO getById(Long id) {
        return ConvertUtils.sourceToTarget(sysUserDao.selectById(id),SysUserDTO.class);
    }

    @Override
    public List<Map<String,Object>> esQuery(EsQueryParam param) {
        List<Map<String,Object>> resultMap = new ArrayList<>();
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 多字段模糊匹配  地址 用户名 邮箱
        builder.query(QueryBuilders.multiMatchQuery(param.getText(),"username","address","email"));
        SearchRequest searchRequest = new SearchRequest(new String[]{"sys-accounts"}, builder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(searchResponse);
        assert searchResponse != null;
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            resultMap.add(hit.getSourceAsMap());
        }
        return resultMap;
    }
}