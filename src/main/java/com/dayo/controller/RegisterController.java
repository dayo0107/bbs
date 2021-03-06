package com.dayo.controller;

import com.dayo.pojo.UserStates;
import com.dayo.pojo.User;
import com.dayo.service.UserRoleService;
import com.dayo.service.UserService;
import com.dayo.util.MailUtil;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author DayoWong on 2018/9/19
 *注册 邮箱激活
 */
@Controller
@RequestMapping("")
public class RegisterController {

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

//    //注册 发送激活邮件
//    @RequestMapping(value = "addUser" ,method = RequestMethod.POST)
//    public String addUser(Model model , User user){
//
//        boolean userIsINACTIVATION =false;
//
//        // 这里可以验证各字段是否为空
//        if (user.getUsername().isEmpty()||user.getPassword().isEmpty()){
//            model.addAttribute("msg","用户名或密码不能为空！");
//            return "result";
//        }
//
//        //利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
//        if(!user.getEmail().matches("^\\w+@(\\w+\\.)+\\w+$")){
//            model.addAttribute("msg","邮箱不符合格式！");
//            return "result";
//        }
//
//        //用户名唯一
//        User userInDB = userService.get(user.getUsername());
//        if(null != userInDB && userInDB.getState() != UserStates.INACTIVATION){
//            model.addAttribute("msg","用户名已存在");
//            return "result";
//        }
//        if(null != userInDB && userInDB.getState() == UserStates.INACTIVATION)
//            userIsINACTIVATION = true;
//
//        //邮箱唯一暂不设置 便于测试
//
//        //生成激活码,md5password,salt,和设置状态
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-mm");//激活码有效时间一分钟
//        System.out.println(format.format(new Date()));
//        String code = UUID.randomUUID().toString();
//        String md5code =new Md5Hash(code+format.format(new Date())).toString();
//        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
//
//        user.setCode(md5code);
//        user.setSalt(salt);
//        user.setState(UserStates.INACTIVATION);
//        user.setPassword(new SimpleHash("md5",user.getPassword(),salt,2).toString());//MD5加密密码
//
//        //保存到数据库
//        try {
//                if (userIsINACTIVATION) {     //注册过但未激活则更新激活码
//                    user.setId(userInDB.getId());
//                    userService.update(user);
//                }
//                else
//                    userService.add(user);
//                //保存或更新成功则通过线程的方式给用户      !!!发送一封邮件!!!
//                new Thread( new MailUtil(user.getEmail(),code) ).start();
//                model.addAttribute("msg", "请到邮箱进行激活操作");
//                return "result";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("msg","数据保存失败");
//            return "result";
//        }
//    }

    //ajax异步注册
    @ResponseBody
    @RequestMapping(value = "addUser" ,method = RequestMethod.POST)
    public String addUser(Model model , User user){

        boolean userIsINACTIVATION =false;

        // 这里可以验证各字段是否为空
        if (user.getUsername().isEmpty()||user.getPassword().isEmpty()){
            return "用户名或密码不能为空！";
        }

        //利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
        if(!user.getEmail().matches("^\\w+@(\\w+\\.)+\\w+$")){
            return "邮箱不符合格式！";
        }

        //用户名唯一
        User userInDB = userService.get(user.getUsername());
        if(null != userInDB && userInDB.getState() != UserStates.INACTIVATION){
            return "用户名已存在";
        }
        if(null != userInDB && userInDB.getState() == UserStates.INACTIVATION)
            userIsINACTIVATION = true;

        //邮箱唯一暂不设置 便于测试

        //生成激活码,md5password,salt,和设置状态
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-mm");//激活码有效时间一分钟
        System.out.println(format.format(new Date()));
        String code = UUID.randomUUID().toString();
        String md5code =new Md5Hash(code+format.format(new Date())).toString();
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();

        user.setCode(md5code);
        user.setSalt(salt);
        user.setState(UserStates.INACTIVATION);
        user.setPassword(new SimpleHash("md5",user.getPassword(),salt,2).toString());//MD5加密密码

        //保存到数据库
        try {
                if (userIsINACTIVATION) {     //注册过但未激活则更新激活码
                    user.setId(userInDB.getId());
                    userService.update(user);
                }
                else
                    userService.add(user);
                //保存或更新成功则通过线程的方式给用户      !!!发送一封邮件!!!
                new Thread( new MailUtil(user.getEmail(),code) ).start();
            return "请到邮箱进行激活操作";

        } catch (Exception e) {
            e.printStackTrace();
            return "数据保存失败";
        }
    }
    //通过邮件链接激活
    @RequestMapping("active")
    @Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
    public String active(Model model , @RequestParam("code") String code){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-mm");
        System.out.println(format.format(new Date()));
        String md5code = new Md5Hash(code+format.format(new Date())).toString();
        User user = userService.getByCode(md5code);

        if (null != user){
            if(user.getState() == UserStates.ACTIVATION){
                model.addAttribute("msg", "账户已经激活");
                return "result";
            }
            user.setState(UserStates.ACTIVATION);
            try {
                userService.update(user);                           //更新用户State
                userRoleService.addRole(user,UserStates.ACTIVATION);//增加用户 普通权限
                model.addAttribute("msg", "账户激活成功");
                return "result";

            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("msg","数据更新失败");
                return "result";
            }
        }
        model.addAttribute("msg","激活码不正确或已过时");
        return "result";
    }
}
