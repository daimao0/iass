package com.aiit.service;

import com.aiit.domain.SysMember;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;

/**
 *
 *  @author 呆毛
 */
public interface SysMemberService extends IService<SysMember>{

    /**
     * 通过手机号查询用户
     * @param id
     * @return
     */
    String getSysMemberPhone(String id);
}
