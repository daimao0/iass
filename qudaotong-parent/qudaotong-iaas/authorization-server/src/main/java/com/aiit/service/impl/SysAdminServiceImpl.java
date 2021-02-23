package com.aiit.service.impl;

import com.aiit.domain.SysAdmin;
import com.aiit.mapper.SysAdminMapper;
import com.aiit.service.SysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 呆毛
 */
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {
    @Resource
    private SysAdminMapper sysAdminMapper;

    @Override
    public String getSysAdminUsername(String id) {
        return sysAdminMapper.getSysAdminUsername(id);
    }
}
