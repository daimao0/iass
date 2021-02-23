package com.aiit.mapper;

import com.aiit.domain.SysMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 *
 *  @author 呆毛
 */
public interface SysMemberMapper extends BaseMapper<SysMember> {

    @Select("select `phone` from sys_member where id = #{id}")
    String getSysMemberPhone(String id);
}