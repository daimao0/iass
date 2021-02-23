package com.aiit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 呆毛
 */
@Configuration
@MapperScan("com.aiit.mapper")
public class MybatisPlusConfig {
}
