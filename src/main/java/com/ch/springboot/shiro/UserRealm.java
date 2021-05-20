package com.ch.springboot.shiro;
import com.ch.springboot.model.User;
import com.ch.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
     UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了doGetAuthorizationInfo授权");
        SimpleAuthorizationInfo   info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
         User principal=(User) subject.getPrincipal();//从认证拿到User对象

        //设置当前用户的权限
        info.addStringPermission(principal.getPerms());
        return info;
    }
   //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了doGetAuthorizationInfo认证");
        //链接数据库
      UsernamePasswordToken  userToken =(UsernamePasswordToken) authenticationToken;
      User user = userService.queryUserByname(userToken.getUsername());
        System.out.println(user);
       if (user==null){
           return  null;
       }
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

        //密码认证
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");

    }


}
