package com.dayo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DayoWong on 2018/9/26
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @RequestMapping("")
    public String admin(Model model){
        model.addAttribute("msg","hello");
        return "admin/index";
    }
    @RequestMapping("listUser")
    public String listUser(Model model){
        model.addAttribute("msg","hi");
        return "admin/listUser";
    }
}
