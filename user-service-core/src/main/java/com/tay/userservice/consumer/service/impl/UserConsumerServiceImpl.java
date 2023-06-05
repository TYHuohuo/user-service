package com.tay.userservice.consumer.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tay.userservice.consumer.service.UserConsumerService;
import com.tay.userservice.dto.SysUserDTO;
import com.tay.userservice.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author tay
 * @since 2022-05-15
 */
@Service
@Slf4j
public class UserConsumerServiceImpl implements UserConsumerService {
    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private SysUserService sysUserService;
    @Override
    public void deleteById(Long id) {
        DeleteRequest request = new DeleteRequest("sys-accounts", id.toString());
        // 发送请求
        try {
            log.info("es删除数据");
            client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("es删除数据错误", e);
        }

    }

    @Override
    public void insertById(Long id) {
        SysUserDTO userDTO = sysUserService.getById(id);
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (userDTO != null) {
                String string = mapper.writeValueAsString(userDTO);
                // 1.准备Request对象
                IndexRequest request = new IndexRequest("sys-accounts").id(id.toString());
                // 2.准备Json文档
                request.source(string, XContentType.JSON);
                // 3.发送请求
                client.index(request, RequestOptions.DEFAULT);
            } else {
                log.error("消息数据对应数据库内容为空");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(Long id) {
        SysUserDTO userDTO = sysUserService.getById(id);
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (userDTO != null) {
                String string = mapper.writeValueAsString(userDTO);
                // 1.准备Request对象
                IndexRequest request = new IndexRequest("sys-accounts").id(id.toString());
                // 2.准备Json文档
                request.source(string, XContentType.JSON);
                // 3.发送请求
                client.index(request, RequestOptions.DEFAULT);
            } else {
                log.error("消息数据对应数据库内容为空");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
