package com.tay.userservice.consumer.service;

/**
 * <p>
 *
 * </p>
 *
 * @author tay
 * @since 2022-05-15
 */
public interface UserConsumerService {
    /**
     * 删除消息
     * @param id
     */
    void deleteById(Long id);

    /**
     * 插入数据消息
     * @param id
     */
    void insertById(Long id);

    /**
     * 更新消息
     * @param id
     */
    void updateById(Long id);
}
