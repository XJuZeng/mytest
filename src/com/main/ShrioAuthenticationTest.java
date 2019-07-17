package com.main;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import  org.apache.shiro.mgt.SecurityManager;

import javax.security.auth.Subject;


public class ShrioAuthenticationTest {
    @Test
    public void Authenticator(){
        //创建工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //通过工厂实例化一个securityManager
        SecurityManager securityManger = factory.getInstance();
        //设置进运行环境
        SecurityUtils.setSecurityManager(securityManger);
        //获取subject
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("username", "xiaojz");
        //登录验证
        subject.login(token);
        //判断
        /*if(subject.isAuthenticated()){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }*/
        //断言用户已经登录
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
    }
}
