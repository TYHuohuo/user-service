package com.tay.userservice.config;

import com.tay.common.constants.MqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author tay
 * @since 2022-05-15
 */
@Configuration
public class MqConfig {
    /**
     * 声明交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MqConstants.ACCOUNT_EXCHANGE);
    }

    /**
     * 声明新增队列
     * @return
     */
    @Bean
    public Queue insertQueue() {
        return new Queue(MqConstants.ACCOUNT_INSERT_QUEUE);
    }

    /**
     * 声明更新队列
     * @return
     */
    @Bean
    public Queue updateQueue() {
        return new Queue(MqConstants.ACCOUNT_UPDATE_QUEUE);
    }

    /**
     * 声明删除队列
     * @return
     */
    @Bean
    public Queue deleteQueue() {
        return new Queue(MqConstants.ACCOUNT_DELETE_QUEUE);
    }

    /**
     * 新增队列绑定
     * @return
     */
    @Bean
    public Binding insertQueueBinding() {
        return BindingBuilder.bind(insertQueue()).to(topicExchange()).with(MqConstants.ACCOUNT_INSERT_KEY);
    }

    /**
     * 新增队列绑定
     * @return
     */
    @Bean
    public Binding updateQueueBinding() {
        return BindingBuilder.bind(updateQueue()).to(topicExchange()).with(MqConstants.ACCOUNT_INSERT_KEY);
    }

    /**
     * 新增队列绑定
     * @return
     */
    @Bean
    public Binding deleteQueueBinding() {
        return BindingBuilder.bind(deleteQueue()).to(topicExchange()).with(MqConstants.ACCOUNT_DELETE_KEY);
    }
}
