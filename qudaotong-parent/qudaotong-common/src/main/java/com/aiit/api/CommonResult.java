package com.aiit.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 呆毛
 */
@Data
@ApiModel(description = "Result", value = "Result")
public class CommonResult<T> {

    @ApiModelProperty(value = "返回状态码", required = true)
    private long code;

    @ApiModelProperty(value = "返回信息", required = true)
    private String message;

    @ApiModelProperty(value = "返回数据", required = true)
    private T data;

    @ApiModelProperty(value = "返回时间",required = true)
    private long showTime;

    protected CommonResult() {
    }

    protected CommonResult(long code, String message, T data, long showTime) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.showTime = showTime;
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), data, 2000);
    }


    /**
     * 成功返回结果和msg
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data, 2000);
    }

    /**
     * 失败返回msg
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null, 2000);
    }


    /**
     * 未登录返回结果
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized(String message) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), message, null, 2000);
    }


    /**
     * 未授权返回结果
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> forbidden(String message) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), message, null, 2000);
    }

}

