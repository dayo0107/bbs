package com.dayo.controller;

import com.dayo.pojo.Post;
import com.dayo.service.PostService;
import com.dayo.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author DayoWong on 2018/9/20
 */
@Controller
@RequestMapping("")
public class PageController {

    @Autowired
    PostService postService;
    @Autowired
    ReplyService replyService;
    //直接访问的页面
    @RequestMapping("home")
    public String home(Model model ){
        List<Post> posts = postService.list();
        for (Post p :
                posts) {
            p.setReplyNum(replyService.listByPid(p.getId()).size());//bug 多余的搜索reply
        }
        model.addAttribute("posts",posts);
        return "bbs/home";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping("unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
