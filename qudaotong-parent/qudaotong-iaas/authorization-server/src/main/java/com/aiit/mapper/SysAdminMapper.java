package com.aiit.mapper;

import com.aiit.domain.SysAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 *
 *  @author 呆毛
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {


    @Select("select `username` from sys_admin where username = #{id}")
    String getSysAdminUsername(String id);
}