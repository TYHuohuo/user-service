package com.tay.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tay.userservice.dao.SysUserDao;
import com.tay.userservice.dto.SysUserDTO;
import com.tay.userservice.entity.SysUserEntity;
import com.tay.userservice.service.SysUserService;
import com.tay.userservice.utils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户
 *
 * @author tay
 * @since 1.0.0 2023-05-29
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;
    @Override
    public void save(SysUserDTO sysUserDTO) {
        SysUserEntity sysUserEntity = ConvertUtils.sourceToTarget(sysUserDTO, SysUserEntity.class);
        sysUserDao.insert(sysUserEntity);
    }

    @Override
    public void deleteById(Long id) {
        sysUserDao.deleteById(id);
    }

    @Override
    public void updateById(SysUserDTO sysUserDTO) {
        SysUserEntity sysUserEntity = ConvertUtils.sourceToTarget(sysUserDTO, SysUserEntity.class);
        sysUserDao.updateById(sysUserEntity);
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
}