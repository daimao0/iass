package com.aiit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author 呆毛
 * @date 2021/1/8 14:24
 */
@EnableAuthorizationServer  //开启授权服务器
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Qualifier("userServiceDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 添加第三方客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //第三方客户端的名称
                .withClient("qudaotong-api")
                //第三方客户端的密钥
                .secret(passwordEncoder.encode("qudaotong-secret"))
                //第三方客户端的授权范围
                .scopes("all")
                .authorizedGrantTypes("password", "refresh_token")
                //token的有效期
                .accessTokenValiditySeconds(7 * 24 * 3600)
                //refresh_token的有效期
                .refreshTokenValiditySeconds(30 * 24 * 3600)
                .and()
                //内部应用的客户端
                .withClient("inside-app")
                .secret(passwordEncoder.encode("inside-secret"))
                .authorizedGrantTypes("client_credentials")
                .scopes("all")
                .accessTokenValiditySeconds(7 * 24 * 3600);

        super.configure(clients);
    }

    /**
     * 配置验证管理器,UserDetailsService
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                //tokenStore存储token
                .tokenStore(jwtTokenStore())
                .tokenEnhancer(jwtAccessTokenConverter());
        super.configure(endpoints);
    }

    public TokenStore jwtTokenStore() {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        return jwtTokenStore;
    }

    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        //加载我们的私钥
        ClassPathResource classPathResource = new ClassPathResource("qudaotong.jks");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, "qudaotong".toCharArray());
        tokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("qudaotong", "qudaotong".toCharArray()));
        return tokenConverter;
    }


//    public TokenStore redisTokenStore(){
//        return new RedisTokenStore(redisConnectionFactory);
//    }
public static void main(String[] args) {
    String encode = new BCryptPasswordEncoder().encode("");
    System.out.println(encode);
    Jwt decode = JwtHelper.decode("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTQzMTcxOTksInVzZXJfbmFtZSI6IjEiLCJhdXRob3JpdGllcyI6WyLotoXnuqfnrqHnkIblkZjmnYPpmZAiXSwianRpIjoiNDlmZGZjNzAtY2ZlOS00NDZhLTliZmItMGExZDNjNGZiYjE5IiwiY2xpZW50X2lkIjoicXVkYW90b25nLWFwaSIsInNjb3BlIjpbImFsbCJdfQ.T_KNLljCzioW5cw_KxLqVnGqVhYq1jXmhkNw9N4uEQLYs07UmePZDh0vgmzRdC2Icyh-THQxLalgd2_Ab5A6EtQEMW6JQP_kQPcSuAdnn4rWLKh5l5cxyrBqvEZFONE8J9pAR3Llq175gGihpWAWguE6WVszdjLjEDV9PdoyE7hmkxrtK5h4uvlUr4fCnVzcTYz1-iIYWQooe-9XtLuoz5eTG1f1Z3EX1mRbI1nIJ81ykVu8TjevA-Ykn_bPJiqHDiYtC_I8czfZhTvH7aObyUFFSGZJbXWfuvC8GFVLbF8cIslRk29RwUtpzuxTUMhiwgGeFUsLjiAVEaoCgbIRBQ");
    System.out.println(decode.getClaims());
}
}

