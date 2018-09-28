package com.dayo.controller;

import com.dayo.pojo.Post;
import com.dayo.service.PostService;
import com.dayo.service.ReplyService;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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
            p.setReplyNum(replyService.listByPid(p.getId()).size());//bug 多余的搜索reply其他内容，应该建立中间表
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

    @RequestMapping("result")
    public String result(){
        return "result";
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
