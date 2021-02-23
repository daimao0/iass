package com.aiit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 *
 *  @author 呆毛
 */
/**
    * 系统管理员表
    */
@Data
@TableName(value = "sys_admin")
public class SysAdmin {
    /**
     * 管理员id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 后台人员用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 后台人员密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 后台人员昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 后台人员手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 后台人员邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 修改人
     */
    @TableField(value = "modify_by")
    private String modifyBy;

    /**
     * 账号创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 上次修改的时间
     */
    @TableField(value = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 上次登录的时间
     */
    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    /**
     * 权限，多个权限用‘,’隔开
     */
    @TableField(value = "authority")
    private String authority;

    /**
     * 状态：0->禁用，1->可用
     */
    @TableField(value = "status")
    private Boolean status;
}