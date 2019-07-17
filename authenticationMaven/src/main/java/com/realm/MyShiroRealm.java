package com.realm;

import com.model.User;
import com.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    //执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串("user:add");
        info.addStringPermission("user:add");
        info.addStringPermission("user:update");
        return info;
    }
    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //假定用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUser(token.getUsername());
        if(user==null){
            return null;//shiro底层抛出unknownAccountException
        }
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
