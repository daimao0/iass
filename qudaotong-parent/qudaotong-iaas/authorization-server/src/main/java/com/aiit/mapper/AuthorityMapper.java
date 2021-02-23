package com.aiit.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 呆毛
 */

public interface AuthorityMapper {

    @Select("SELECT sys_authority.`authority_name`,sys_role_authority.role_id\n" +
            "from sys_authority \n" +
            "INNER JOIN sys_role_authority on sys_authority.id = sys_role_authority.authority_id\n" +
            "INNER JOIN sys_member_role on sys_member_role.role_id = sys_role_authority.role_id\n" +
            "where sys_member_role.member_id = #{memberId}")
    List<String> getAuthorityByMemberId(String memberId);
}
