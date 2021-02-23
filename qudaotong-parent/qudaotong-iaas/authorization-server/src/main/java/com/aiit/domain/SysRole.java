package com.aiit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 *  @author 呆毛
 */
/**
    * 系统角色表
    */
@Data
@TableName(value = "sys_role")
@Accessors(chain = true)
public class SysRole {
    /**
     * 角色表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private String createTime;
}