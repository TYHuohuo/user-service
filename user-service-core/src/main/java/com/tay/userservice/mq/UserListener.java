package com.tay.userservice.mq;

import com.tay.common.constants.MqConstants;
import com.tay.userservice.consumer.service.UserConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 消息监听
 * </p>
 *
 * @author tay
 * @since 2022-05-15
 */
@Component
@Slf4j
public class UserListener {
    @Autowired
    private UserConsumerService userConsumerService;

    @RabbitListener(queues = MqConstants.ACCOUNT_INSERT_QUEUE)
    public void listenerInsert(Long id) {
        log.info("监听到新增消息");
        userConsumerService.insertById(id);
    }

    @RabbitListener(queues = MqConstants.ACCOUNT_UPDATE_QUEUE)
    public void listenerUpdate(Long id) {
        log.info("监听到更新消息");
        userConsumerService.updateById(id);
    }

    @RabbitListener(queues = MqConstants.ACCOUNT_DELETE_QUEUE)
    public void listenerDelete(Long id) {
        log.info("监听到删除消息");
        userConsumerService.deleteById(id);
    }
}
