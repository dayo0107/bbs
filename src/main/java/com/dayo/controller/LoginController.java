package com.dayo.controller;

import com.dayo.pojo.User;
import com.dayo.pojo.UserStates;
import com.dayo.service.UserService;
import com.dayo.util.MailUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author DayoWong on 2018/9/20
 * 登陆、忘记/重置密码
 *
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/login/alterPassword",method = RequestMethod.POST)
    public String alterPassword(String username , String code , String password ,Model model){
        password = password.trim();
        if(!code.isEmpty()&& !password.isEmpty()) {
            User user = userService.get(username);
            if (user != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-mm");
                System.out.println(format.format(new Date()));
                String md5code = new Md5Hash(code + format.format(new Date())).toString();

                if (user.getCode().equals(md5code)) {
                    String salt = new SecureRandomNumberGenerator().nextBytes().toString();
                    String md5Password = new SimpleHash("md5", password, salt, 2).toString();

                    user.setSalt(salt);
                    user.setPassword(md5Password);
                    try {
                        userService.update(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                        model.addAttribute("msg","数据更新失败");
                        return "result";
                    }
                    model.addAttribute("msg","修改密码成功");
                    return "result";
                }
            }
        }
        model.addAttribute("msg","验证码已过时或不正确");
        return "result";
    }

    @ResponseBody
    @RequestMapping(value = "/login/checkEmail" ,method = RequestMethod.POST)
    public String checkEmail(@RequestParam String username ,@RequestParam String email , Model model){
        User user = userService.get(username);
        if(user == null)
            return "用户名不存在";
        else if ( !email.equals(user.getEmail()) ){
            return "用户名或邮箱不正确";
        }
        else if (user.getState() == UserStates.INACTIVATION){
            return "账户未激活,请前往注册页面激活";
        }
        else{
            //生成激活码,md5password,salt,和设置状态
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-mm");//激活码有效时间一分钟
            String code = UUID.randomUUID().toString();

            System.out.println(format.format(new Date()));
            System.out.println("code:"+code);

            String md5code =new Md5Hash(code+format.format(new Date())).toString();
            user.setCode(md5code);
            userService.update(user);
            //通过线程的方式给用户      !!!发送一封邮件!!!
            new Thread( new MailUtil(user.getEmail(),code ,code) ).start();
            return "success";
        }
    }

    @RequestMapping(value = "/login/forget" ,method = RequestMethod.GET)
    public String forgetPassword(@RequestParam String username ,Model model){
        model.addAttribute("username",username);
        return "forget";
    }

    @ResponseBody
    @RequestMapping(value = "/login/checkUsername" ,method = RequestMethod.POST)
    public String checkUsername(Model model ,String username){
        HtmlUtils.htmlEscape(username);
        if (userService.get(username) !=null)
            return "success";
        return username;
    }

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
