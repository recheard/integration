package com.order.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author recheard
 * @description:
 * @date 2021/5/316:46
 */
@Configuration
public class TokenConfig {

    /**
     * 配置token的持久化方式
     */
    private String SIGNING_KEY = "uaa123";

    /**
     * 设置token通过jwt存储
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        //使用内存存储
        //return new InMemoryTokenStore();
        //jwt加密秘钥
        return new JwtTokenStore(accessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY); //对称加密秘钥，资源服务器使用该秘钥来验证
        return converter;
    }

}
