package com.aiit.service.impl;

import com.aiit.domain.SysMember;
import com.aiit.domain.SysMemberRole;
import com.aiit.domain.SysRole;
import com.aiit.service.MemberService;
import com.aiit.service.SysMemberRoleService;
import com.aiit.service.SysMemberService;
import com.aiit.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 呆毛
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    private SysMemberService sysMemberService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysMemberRoleService sysMemberRoleService;

    private final static String FIRST_MEMBER_ROLE = "普通用户";
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void firstCreateMember(String phone,String memberId) {
        //创建用户
        SysMember sysMember = new SysMember()
                .setId(Long.parseLong(memberId))
                .setPhone(phone)
                .setPassword(null)
                .setPassword(null)
                .setStatus(true)
                .setCreateTime(new Date());
        sysMemberService.save(sysMember);
        //创建默认用户权限
        SysRole role = sysRoleService.getOne(new QueryWrapper<SysRole>().eq("role_name", FIRST_MEMBER_ROLE));
        //插入用户角色表中
        SysMemberRole sysMemberRole = new SysMemberRole()
                .setMemberId(sysMember.getId())
                .setRoleId(role.getId());
        sysMemberRoleService.save(sysMemberRole);

    }

    /**
     * 重置密码
     *
     * @param memberId
     * @param password
     * @return
     */
    @Override
    public Boolean resetPassword(String memberId, String password) {
        String encode = new BCryptPasswordEncoder().encode(password);
        SysMember sysMember = new SysMember()
                .setId(Long.parseLong(memberId))
                .setPassword(encode);
        return sysMemberService.saveOrUpdate(sysMember);
    }

}
