package com.ch.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.ch.springboot.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFillterFactoryBean:3
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
       //添加Shiro的内置过滤器
        /*
        * anon:无需认证就可以访问
        * authc:必须认证了才能访问
        * user:必须拥有 记住我 功能才能使用
        * perms: 拥有对某个资源的权限才能访问
        * role: 拥有某个角色权限才能访问
        * */
        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权，正常情况下，没用授权跳到授权页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

//        filterMap.put("/user/*","authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //设置未授权的请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");

         return shiroFilterFactoryBean;
    }

    //DafaultWebSecurityManager:2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager  getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager   securityManager = new DefaultWebSecurityManager();
         //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建 realm 对象, 需要自定义类:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }




    @Bean
   //整合ShiroDiolect用来整合 shrio thyme leaf
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
