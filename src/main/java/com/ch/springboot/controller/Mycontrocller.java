package com.ch.springboot.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class Mycontrocller {
    @RequestMapping(value = "/index")
    public  String toIndex(Model model){
        model.addAttribute("msg","hello shior");
        return "index";
    }

    @RequestMapping(value =  "user/add")
    public String add(){
        return  "user/add";
    }
    @RequestMapping(value =  "user/update")
    public String update(){
        return  "user/update";
    }
    @RequestMapping(value = "/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login(String username,String password,Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken  token= new UsernamePasswordToken(username,password);
        try{

            //这个就是跳到 UserRealm
            subject.login(token);//执行登录方法,如果没有异常就说明ok了
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg", "用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){//密码不存在
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
    @RequestMapping("/noauth")
    @ResponseBody
    public String Unauthorized(){
        return "无法访问";
    }
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession httpSession, Model model){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","安全退出");
        return "login";
    }

}
