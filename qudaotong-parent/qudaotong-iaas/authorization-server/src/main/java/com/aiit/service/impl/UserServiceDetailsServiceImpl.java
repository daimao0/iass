package com.aiit.service.impl;

import com.aiit.constant.LoginConstant;
import com.aiit.domain.SysAdmin;
import com.aiit.domain.SysMember;
import com.aiit.mapper.AuthorityMapper;
import com.aiit.service.SysAdminService;
import com.aiit.service.SysMemberService;
import com.aiit.utils.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 呆毛
 */
@Service
public class UserServiceDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysAdminService sysAdminService;
    @Resource
    private SysMemberService sysMemberService;
    @Resource
    private AuthorityMapper authorityMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //区分是后台人员还是用户登录
        String loginType = requestAttributes.getRequest().getParameter("login_type");
        if (StringUtils.isEmpty(loginType)) {
            throw new AuthenticationServiceException("登录类型不能为null");
        }
        String grantType = requestAttributes.getRequest().getParameter("grant_type");
        if (LoginConstant.REFRESH_TYPE.equals(grantType.toUpperCase())){
            username = adjustUsername(username,loginType);
        }
        switch (loginType) {
            case LoginConstant.ADMIN_TYPE:
                return loadSysAdminByUsername(username);
            case LoginConstant.MEMBER_TYPE:
                return loadSysMemberByUsername(username);
            case LoginConstant.AUTH_CODE_TYPE:
                return loadsSysMemberByAuthCode(username);
            default:
                throw new AuthenticationServiceException("暂不支持的登录方式:" + loginType);
        }
    }



    /**
     * 纠正用户名称
     * @param username
     * @param loginType
     * @return
     */
    private String adjustUsername(String username, String loginType) {

        if (LoginConstant.ADMIN_TYPE.equals(loginType)){
            //管理员的纠正方式
            return sysAdminService.getSysAdminUsername(username);
        }
        if (LoginConstant.MEMBER_TYPE.equals(loginType)){
            //会员的纠正方式
            return sysMemberService.getSysMemberPhone(username);
        }
        return username;
    }

    /**
     * 后台人员的登录
     *
     * @param username
     * @return
     */
    private UserDetails loadSysAdminByUsername(String username) {
        //1、查询用户
        QueryWrapper<SysAdmin> queryWrapper = new QueryWrapper<SysAdmin>().eq("phone", username);
        SysAdmin sysAdmin = sysAdminService.getOne(queryWrapper);
        if (sysAdmin ==null){
            throw new UsernameNotFoundException("该用户不存在");
        }
        //获得用户权限
        Collection<? extends GrantedAuthority> sysUserAuthority = getSysUserAuthority(sysAdmin.getAuthority());
        if (sysUserAuthority==null){
            throw new AuthenticationServiceException("用户账户异常，没有任何权限，请联系管理员");
        }
        return new User(sysAdmin.getId().toString(),
                sysAdmin.getPassword(),
                sysAdmin.getStatus(),
                true,
                true,
                true,
                sysUserAuthority);

    }

    /**
     * 通过用户id查询用户的权限
     * @param memberId 用户id
     * @return
     */
    private Collection<? extends GrantedAuthority> getSysUserAuthority(String memberId) {
        //1、当用户为超级管理员时，他拥有所有的权限数据
        List<String> authorityList = authorityMapper.getAuthorityByMemberId(memberId);
        if (authorityList==null){
            return null;
        }
        return authorityList.stream().distinct()//去重
                .map(perm->new SimpleGrantedAuthority(perm))
                .collect(Collectors.toSet());
    }
    /**
     * 会员的登录
     *
     * @param username 这里用户的username就是phone
     * @return
     */
    private UserDetails loadSysMemberByUsername(String username) {
        //1、查询用户
        QueryWrapper<SysMember> queryWrapper = new QueryWrapper<SysMember>().eq("phone", username);
        SysMember sysMember = sysMemberService.getOne(queryWrapper);
        if (sysMember==null){
            throw new UsernameNotFoundException("该用户不存在");
        }
        //获得用户权限
        Collection<? extends GrantedAuthority> sysUserAuthority = getSysUserAuthority(sysMember.getId().toString());
        if (sysUserAuthority==null){
            throw new AuthenticationServiceException("用户账户异常，没有任何权限，请联系管理员");
        }
        return new User(sysMember.getId().toString(),
                sysMember.getPassword(),
                sysMember.getStatus(),
                true,
                true,
                true,
                sysUserAuthority);
    }
    /**
     * 会员的登录
     *
     * @param username 这里用户的username就是phone
     * @return
     */
    private UserDetails loadsSysMemberByAuthCode(String username) {
        //1、查询用户
        QueryWrapper<SysMember> queryWrapper = new QueryWrapper<SysMember>().eq("phone", username);
        SysMember sysMember = sysMemberService.getOne(queryWrapper);
        if (sysMember==null){
            throw new UsernameNotFoundException("该用户不存在");
        }
        //获得用户权限
        Collection<? extends GrantedAuthority> sysUserAuthority = getSysUserAuthority(sysMember.getId().toString());
        if (sysUserAuthority==null){
            throw new AuthenticationServiceException("用户账户异常，没有任何权限，请联系管理员");
        }
        return new User(sysMember.getId().toString(),
                new BCryptPasswordEncoder().encode(""),
                sysMember.getStatus(),
                true,
                true,
                true,
                sysUserAuthority);
    }
}
