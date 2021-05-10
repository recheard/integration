package com.security.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;


/**
 * @author recheard
 * @description:
 * @date 2021/4/2617:15
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //将客户端信息从数据库读取
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService)
                clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 配置客户端信息服务
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients.withClientDetails(clientDetailsService);
        // 使用内存的方式存储
        /*clients.inMemory()
            // client_id 客户端id比如我们的订单的服务上的id
            .withClient("order")
            //客户端秘钥
            .secret(new BCryptPasswordEncoder().encode("secret"))
            //客户端允许访问的资源列表
            .resourceIds("res1")
            // 配置该client允许的授权类型
            .authorizedGrantTypes("authorization_code",
                "password","client_credentials","implicit","refresh_token")
            // 允许的授权范围,如果为all则代表能访问所有服务,也可以指定能访问的服务的服务名数组
            .scopes("all")
            //false跳转到授权页面，如果为true则直接返回令牌
            .autoApprove(false)
            //加上验证回调地址
            .redirectUris("http://www.baidu.com");*/
            //上面的只是配置了一个客户端如果需要配置多个可以再比如上面的.redirectUris方法后面继续点withClient方法添加新的客户端
    }

    /**
     * 配置令牌信息
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service=new DefaultTokenServices();
        //设置客户端信息服务
        service.setClientDetailsService(clientDetailsService);
        //是否产生刷新令牌
        service.setSupportRefreshToken(true);
        //令牌存储策略
        service.setTokenStore(tokenStore);
        //令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);
        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }

    /**
     * 配置令牌访问端点
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager) //授权类型中密码模式需要
                .authorizationCodeServices(authorizationCodeServices) //授权类型中授权码模式需要
                .tokenServices(tokenService())// 令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); //允许post提交
    }

    /**
     * 设置授权码模式的授权码如何存取，
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        //从内存汇中存取
        //return new InMemoryAuthorizationCodeServices();
        //从数据库存取
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 令牌访问端点安全策略
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security){
        security
                //tokenkey这个endpoint当使用JwtToken且使用非对称加密时，资源服务用于获取公钥而开放的，这里指这个endpoint完全公开。
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")  //checkToken这个endpoint完全公开
                .allowFormAuthenticationForClients(); //允许表单认证
    }


}
