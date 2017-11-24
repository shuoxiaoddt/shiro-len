package cn.xs.shiro;

import cn.xs.shiro.relative.shirosModel.Perple;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by uwayxs on 2017/11/22.
 */

public class LocalShiroTest{

    @Test
    public void shiroFirst(){
        String username = "daisiSB";
        String password = "SBdaisi";

        IniSecurityManagerFactory factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        AuthenticationToken token
                = new UsernamePasswordToken(username, password);

        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        /**
         * Subject是一个接口,有两个实现类DelegatingSubject,和WebDelegatingSubject
         * DelegatingSubject implements Subject
         * WebDelegatingSubject extends DelegatingSubject implements WebSubject
         */
        try {
            System.out.println(session.getId());
            currentUser.login(token);
            /**
             * 在登录过程中会将token和从realm中获取的info信息(根据token中的username获取)进行比对
             * 如果两对象匹配,则验证通过,否则验证失败
             */
        } catch (IncorrectCredentialsException ice) {
            System.out.println("密码错误");
        } catch (LockedAccountException lae) {
            System.out.println("账户被锁");
        } catch (AuthenticationException ae) {
            System.out.println("验证失败");
        }
    }
    @Test
    public void buildModel(){
        Perple perple = new Perple.Builder().age(11).name("d").sex("难").buildPerple();
        System.out.println(perple);
    }
}
