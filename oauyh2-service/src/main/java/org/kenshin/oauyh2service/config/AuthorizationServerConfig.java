package org.kenshin.oauyh2service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailService;

    // 认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;


    //令牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();
        // 客户端详情服务
        service.setClientDetailsService(clientDetailsService);
        service.setSupportRefreshToken(true);//支持刷新令牌
        service.setTokenStore(tokenStore);//令牌存储策略
        //令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);

        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }

//    /**
//     * access_token存储器
//     * 这里存储在数据库，大家可以结合自己的业务场景考虑将access_token存入数据库还是redis
//     */
//    @Bean
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }

    /**
     * 从数据库读取clientDetails相关配置
     * 有InMemoryClientDetailsService 和 JdbcClientDetailsService 两种方式选择
     */
//    @Bean
//    public ClientDetailsService clientDetails() {
//        return new JdbcClientDetailsService(dataSource);
//    }

    /**
     * 注入密码加密实现器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证服务器Endpoints配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //如果需要使用refresh_token模式则需要注入userDetailService
        endpoints.userDetailsService(userDetailService);
        endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenServices(tokenService());
        endpoints.tokenStore(tokenStore);
    }

    /**
     * 认证服务器相关接口权限管理
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients() //如果使用表单认证则需要加上
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * client存储方式，此处使用jdbc存储
     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetails());
//    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("clientapp")
                .secret("123456")
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600);
    }
}
