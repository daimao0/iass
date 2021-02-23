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
    * 用户角色表啊
    */
@Data
@TableName(value = "sys_member_role")
@Accessors(chain = true)
public class SysMemberRole {
    /**
     * 角色id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "member_id")
    private Long memberId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Integer roleId;
}