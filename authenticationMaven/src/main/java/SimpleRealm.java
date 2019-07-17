import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class SimpleRealm implements Realm{
    //获取Realm名称
    public String getName() {
        return "SinpleRealm";
    }
    //判断realm是否支持token
    public boolean supports(AuthenticationToken authenticationToken) {
        AuthenticationToken token = authenticationToken;
        return token instanceof UsernamePasswordToken;
    }
    //根据token获取认证信息
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        String pwd = new String((char[])token.getCredentials());
        if(!"user".equals(username)){
            throw new UnknownAccountException();
        }else if(!"123".equals(pwd)){
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username,pwd,getName());
    }
}
