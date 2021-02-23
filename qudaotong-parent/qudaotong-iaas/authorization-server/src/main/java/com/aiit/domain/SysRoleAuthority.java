package com.aiit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 *  @author 呆毛
 */
/**
    * 系统角色权限表
    */
@Data
@TableName(value = "sys_role_authority")
public class SysRoleAuthority {
    /**
     * 角色权限表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Integer roleId;

    /**
     * 权限id
     */
    @TableField(value = "authority_id")
    private Integer authorityId;
}