package com.dayo.controller;

import com.dayo.pojo.User;
import com.dayo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author DayoWong on 2018/9/20
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(Model model ,String username ,String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            Session session = subject.getSession();
            User user = userService.get(subject.getPrincipal().toString());
            session.setAttribute("subject",subject);
            session.setAttribute("user",user);
//            model.addAttribute("msg","登陆成功");
            return "redirect:/home";
        } catch (AuthenticationException e) {
            model.addAttribute("msg","验证失败");
            return "result";
        }
    }
}
