package com.tay.common.constants;

/**
 * <p>
 *  MQ常量类
 * </p>
 *
 * @author tay
 * @since 2022-05-15
 */
public class MqConstants {
    /**
     * 交换机
     */
    public static final String ACCOUNT_EXCHANGE = "account.topic";
    /**
     * 新增队列
     */
    public static final String ACCOUNT_INSERT_QUEUE = "account.insert.queue";
    /**
     * 更新队列
     */
    public static final String ACCOUNT_UPDATE_QUEUE = "account.update.queue";
    /**
     * 删除队列
     */
    public static final String ACCOUNT_DELETE_QUEUE = "account.delete.queue";
    /**
     * 新增或修改的路由key
     */
    public static final String ACCOUNT_INSERT_KEY = "account.insert";
    /**
     * 删除路由key
     */
    public static final String ACCOUNT_DELETE_KEY = "account.delete";

}
