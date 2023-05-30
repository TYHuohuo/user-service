package com.tay.userservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author tay
 * @since 2022-05-15
 */
@Getter
@Setter
public class BaseDTO {
    /**
     * 页数
     */
    private Integer page;
    /**
     * 分页大小
     */
    private Integer limit;
}
