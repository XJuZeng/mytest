import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class SecondRealm implements Realm {
    //返回Realm名
    public String getName() {
        return "SecondRealm";
    }
    //查看是否支持这个token
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
    //添加认证信息
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        System.out.println("secondRealm:"+username+":"+password);
        return new SimpleAuthenticationInfo(username,password,this.getName());
    }
}
