package com.dayo.controller;

import com.dayo.pojo.Post;
import com.dayo.pojo.Reply;
import com.dayo.pojo.User;
import com.dayo.service.PostService;
import com.dayo.service.ReplyService;
import com.dayo.service.UserService;
import com.dayo.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author DayoWong on 2018/9/21
 */
@Controller
@RequestMapping("/home")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    ReplyService replyService;
    @Autowired
    UserService userService;

    @RequestMapping("/addPost")
    public String addPost(HttpSession session, Post post ,Model model){
        try {
            User user =(User) session.getAttribute("user");
            if(user ==null )
                return "redirect:/login";

            Date date = new Date();
            post.setCreateDate(date);
            post.setLastDate(date);
            post.setUid(user.getId());
            postService.add(post);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg","controller:发帖失败");
            return "result";
        }
        return "redirect:/home";
    }

    @RequestMapping("/showPost")
    public String Post(@RequestParam("pid") int pid , Model model , HttpSession session ,Page page){

        Post post = postService.get(pid);

        User user = (User) session.getAttribute("user");
        post.setUser(user);

        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Reply> replies = replyService.listByPid(pid);

        replyService.fillUser(replies);
        post.setReplies(replies);
        int total = (int) new PageInfo<>(replies).getTotal();

        page.setTotal(total);
        page.setParam("&pid=" + pid);
        model.addAttribute("page", page);
        model.addAttribute("post", post);
        return "bbs/post";
    }

    @RequestMapping("/deletePost")
    public String deletePost(@RequestParam int pid){
        postService.delete(pid);
        return "redirect:/home";
    }

    @RequestMapping("/listPostT")
    public String listPostByLastReplyTime(Model model){
        List<Post> posts = postService.listByTime();
        for (Post p :
                posts) {
            p.setUser(userService.getById(p.getUid()));
            p.setReplyNum(replyService.count(p.getId()));
        }
        model.addAttribute("posts",posts);
        return "bbs/home";
    }


}
