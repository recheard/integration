package com.order.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author recheard
 * @description:
 * @date 2021/5/422:57
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    /**
     * 允许访问的资源列表
     */
    public static final String RESOURCE_ID = "res1";

    /**
     * 客户端id，这个id需要在security整整服务的configure(ClientDetailsServiceConfigurer clients)中有注册
     */
    public static final String CLIENT_ID = "order";

    @Autowired
    private TokenStore tokenStore;

    /**
     * 配置客户端信息
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
            //验证令牌的服务
            .tokenStore(tokenStore)
                //.tokenServices(tokenService())
            .stateless(true);
    }
    /**
     * 安全策略
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
            .antMatchers("/**").access("#oauth2.hasScope('ROLE_ADMIN')")
            .and().csrf().disable()
            //不创建session也不适用session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    //资源服务令牌解析服务
    /*@Bean
    public ResourceServerTokenServices tokenService() {
    //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret
        RemoteTokenServices service=new RemoteTokenServices();
        //认证服务器验证地址
        service.setCheckTokenEndpointUrl("http://localhost:8101/oauth/check_token");
        //设置客户端id
        service.setClientId("order");
        //设置秘钥
        service.setClientSecret("secret");
        return service;
    }*/


}
