package com.aiit.config.jatcache;

import org.springframework.context.annotation.Configuration;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;


@Configuration
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.aiit.service.impl")
public class JetCacheConfig {

}
