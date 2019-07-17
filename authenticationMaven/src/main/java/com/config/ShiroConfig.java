package com.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.realm.MyShiroRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean fac = new ShiroFilterFactoryBean();
        //设置安全管理器
        fac.setSecurityManager(securityManager);
        /*
        shiro内置过滤器：anon:无需认证（登录）即可访问 authc:必须认证才能访问 user:如果使用rememberme可以访问
        perms:必须获得资源权限          role:必须得到角色权限
         */
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("/testThymeleaf","anon");
        map.put("/login","anon");
        map.put("/*","authc");
        //授权过滤器
        map.put("/add","perms[user:add]");
        //map.put("/update","perms[user:update]");
        //设置未授权提示页面
        fac.setUnauthorizedUrl("/noAuth");
        //修改登录页面
        fac.setLoginUrl("/toLogin");
        fac.setFilterChainDefinitionMap(map);
        return fac;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")MyShiroRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建REALM
     */
    @Bean(name="userRealm")
    public MyShiroRealm getRealm(){
        return new MyShiroRealm();
    }
    /*
    配置ShiroDialect,用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
