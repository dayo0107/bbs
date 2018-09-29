package com.dayo.controller;

import com.dayo.pojo.User;
import com.dayo.pojo.UserStates;
import com.dayo.service.RoleService;
import com.dayo.service.UserRoleService;
import com.dayo.service.UserService;
import com.dayo.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author DayoWong on 2018/9/26
 * bbs用户权限管理
 *  1.后台管理员操作
 *  2.前台管理员操作
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;


    /*后台管理员操作*/
    @RequestMapping("")
    public String admin(Model model,Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> users = userService.list();
        page.setTotal((int) new PageInfo<>(users).getTotal());

        model.addAttribute("users",users);
        model.addAttribute("page",page);
        model.addAttribute("msg","hello");
        return "admin/index";
    }

    @RequestMapping("banUser")
    public String banUser(@RequestParam int id ){
        User user = userService.getById(id);
        user.setState(UserStates.BANNED);

        userRoleService.updateRole(user,UserStates.BANNED);
        userService.update(user);
        return "redirect:/admin";
    }

    @RequestMapping("muteUser")
    public String muteUser(@RequestParam int id){
        User user = userService.getById(id);
        user.setState(UserStates.MUTE);

        userRoleService.updateRole(user,UserStates.MUTE);
        userService.update(user);
        return "redirect:/admin";
    }

    @RequestMapping("releaseUser")
    public String releaseUser(@RequestParam int id){
        User user = userService.getById(id);
        user.setState(UserStates.RELEASE);

        userRoleService.updateRole(user,UserStates.RELEASE);
        userService.update(user);
        return "redirect:/admin";
    }
    /*前台管理员操作*/
    @RequestMapping("muteFore")
    public String mute(@RequestParam int pid ,@RequestParam int uid){
        User user = userService.getById(uid);
        if(user.getState()!=UserStates.ADMIN) {//防止管理员点到自己
            user.setState(UserStates.MUTE);

            userRoleService.updateRole(user, UserStates.MUTE);
            userService.update(user);
        }
        return "redirect:/home/showPost?pid="+pid;
    }
}
