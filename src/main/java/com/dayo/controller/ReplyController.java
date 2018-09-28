package com.dayo.controller;

import com.dayo.pojo.Post;
import com.dayo.pojo.Reply;
import com.dayo.pojo.User;
import com.dayo.pojo.UserStates;
import com.dayo.service.PostService;
import com.dayo.service.ReplyService;
import com.dayo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;


/**
 * @author DayoWong on 2018/9/21
 */
@Controller
@RequestMapping("/post")
public class ReplyController {
    @Autowired
    ReplyService replyService;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @RequestMapping("/addReply")
    public String addReply(@RequestParam("pid") int pid , Reply reply , HttpSession session , Model model){
        User user = (User) session.getAttribute("user");
        try {
            Post post = postService.get(pid);

            Date date = new Date();

            reply.setPid(pid);
            reply.setUid(user.getId());
            reply.setCreateDate(date);
            reply.setIndex_(replyService.listByPid(pid).size()+1);
            post.setLastDate(date);

            replyService.add(reply);
            postService.update(post);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg","controller:回帖失败");
            return "result";
        }

        return "redirect:/home/showPost?pid="+pid;
    }
}
