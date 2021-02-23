package com.aiit.service.impl;

import com.aiit.domain.SysMember;
import com.aiit.mapper.SysMemberMapper;
import com.aiit.service.SysMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 *
 *  @author 呆毛
 */
@Service
public class SysMemberServiceImpl extends ServiceImpl<SysMemberMapper, SysMember> implements SysMemberService{


    @Override
    public String getSysMemberPhone(String id) {
        return null;
    }


}
