package com.aiit.service;

/**
 * @author 呆毛
 */
public interface MemberService {
    /**
     * 用户第一次创建
     * @param phone
     */
    void firstCreateMember(String phone,String memberId);

    /**
     * 重置密码
     * @param memberId
     * @param password
     * @return
     */
    Boolean resetPassword(String memberId,String password);
}
