import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.apache.shiro.mgt.SecurityManager;

import java.security.Security;

public class AuthenticationTest {
    @Test
    public void test1(){
        //加载ini文件，初始化security
         Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-real.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken user = new UsernamePasswordToken("user", "123");
        subject.login(user);
        if(subject.isAuthenticated()){
            System.out.println("认证成功");
        }else{
            System.out.println("认证失败");
        }
        /*try {
            subject.login(user);
            if(subject.isAuthenticated()){
                System.out.println("认证成功");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("认证失败");
        }*/
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void test2(){
        //创建SecurityManger工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("user", "11");
        try {
            subject.login(token);
            System.out.println("登陆成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        System.out.println(subject.getPrincipal());
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
    }
    @Test
    public void test3(){
        System.out.println(isNumber("123.0"));
        System.out.println(isNumber("12.3.0"));
        System.out.println(isNumber("12"));
        System.out.println(isNumber("-12"));
        System.out.println(isNumber("店"));
    }
    public static boolean isNumber(String str){
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }
}
