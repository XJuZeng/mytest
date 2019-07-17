package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("name","黑马程序员");
        return "test";
    }

    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        return "login";
    }
    @RequestMapping("/add")
    public String add(Model model){
        return "add";
    }
    @RequestMapping("/update")
    public String update(Model model){
        return "update";
    }
    @RequestMapping("/noAuth")
    @ResponseBody
    public String unAuth(){
        return "未经资源授权";
    }
    @RequestMapping("/login")
    public String login(String name,String password,Model model){
        //使用shiro编写认证权限
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);
            return "redirect:/testThymeleaf";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
}
