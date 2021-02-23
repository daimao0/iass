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
    * 公司表
    */
@Data
@TableName(value = "sys_company")
public class SysCompany {
    /**
     * 公司表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司名
     */
    @TableField(value = "company_name")
    private String companyName;

    /**
     * 公司注册app的时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 公司状态：是否还可以使用本app
     */
    @TableField(value = "stats")
    private Boolean stats;

    /**
     * 公司更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 购买时间
     */
    @TableField(value = "purchasing_time")
    private Date purchasingTime;

    /**
     * 购买截止日期
     */
    @TableField(value = "expiration_time")
    private Date expirationTime;
}