package com.aiit.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * web 操作日志记录
 * elk
 * @author 呆毛
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebLog {
    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * uri
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求方法
     */
    private String Method;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 返回结果
     */
    private Object result;
}
