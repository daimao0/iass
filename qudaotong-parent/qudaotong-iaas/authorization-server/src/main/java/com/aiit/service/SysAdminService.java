package com.aiit.service;

import com.aiit.domain.SysAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 *  @author 呆毛
 */
public interface SysAdminService extends IService<SysAdmin>{


    String getSysAdminUsername(String id);
}
