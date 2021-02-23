package com.aiit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 *  @author 呆毛
 */
/**
    * 系统用户表
    */
@Data
@TableName(value = "sys_member")
@Accessors(chain = true)
public class SysMember {
    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;


    /**
     * 帐号状态：0->禁用，1->启用
     */
    @TableField(value = "status")
    private Boolean status;

    /**
     * 注册时间
     */
    @TableField(value = "create_time")
    private Date createTime;


}