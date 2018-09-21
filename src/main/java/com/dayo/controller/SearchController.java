package com.dayo.controller;

import com.dayo.pojo.Post;
import com.dayo.service.PostService;
import com.dayo.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author DayoWong on 2018/9/21
 */
@Controller
public class SearchController {

    @Autowired
    PostService postService;
    @Autowired
    ReplyService replyService;
    @RequestMapping("/search")
    public String search(Model model ,String keyword){
        if(keyword.isEmpty())
            return "redirect:/home";

        List<Post> posts = postService.listByKeyword(keyword);
        model.addAttribute("posts",posts);
        for (Post p :
                posts) {
            p.setReplyNum(replyService.listByPid(p.getId()).size());
        }
        return "bbs/home";
    }
}
