package com.tay.common.common;

import com.tay.common.enums.ResultEnum;

/**
 * <p>
 *  请求结果处理工具
 * </p>
 *
 * @author tay
 * @since 2022-05-15
 */
public class ResultUtil<T> {

    /**
     * 操作成功，返回具体的数据、结果码和提示信息
     *
     * @return 结果
     */
    public static <T> Result<T> success() {
        final Result<T> result = new Result(ResultEnum.OK.getCode(), ResultEnum.OK.getMsg(), null);
        return result;
    }

    public static <T> Result<T> success(String msg) {
        final Result<T> result = new Result(ResultEnum.OK.getCode(), msg, null);
        return result;
    }

    /**
     * 操作失败，返回具体的数据、结果码和提示信息
     *
     * @return 结果
     */
    public static <T> Result<T> fail() {
        final Result<T> result = new Result(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg(), null);
        return result;
    }

    public static <T> Result<T> fail(String msg) {
        final Result<T> result = new Result(ResultEnum.ERROR.getCode(), msg, null);
        return result;
    }

    /**
     * 操作成功，返回具体的数据、结果码和提示信息
     *
     * @param resultEnum 枚举
     * @return 结果
     */
    public static <T> Result<T> success(ResultEnum resultEnum) {
        final Result<T> result = new Result(resultEnum.getCode(), resultEnum.getMsg(), null);
        return result;
    }

    /**
     * 操作失败，返回具体的数据、结果码和提示信息
     *
     * @param resultEnum 枚举
     * @return 结果
     */
    public static <T> Result<T> fail(ResultEnum resultEnum) {
        final Result<T> result = new Result(resultEnum.getCode(), resultEnum.getMsg(), null);
        return result;
    }

}
