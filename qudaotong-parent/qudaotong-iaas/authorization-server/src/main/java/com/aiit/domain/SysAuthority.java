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
    * 权限表
    */
@Data
@TableName(value = "sys_authority")
public class SysAuthority {
    /**
     * 权限表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    @TableField(value = "authority_name")
    private String authorityName;
}