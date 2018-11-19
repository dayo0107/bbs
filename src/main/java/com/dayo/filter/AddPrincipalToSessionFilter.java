package com.dayo.filter;

import com.dayo.pojo.User;
import com.dayo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author DayoWong on 2018/10/10
 * 添加remember cookie 中的用户名到session
 */
public class AddPrincipalToSessionFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;
    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();

        if(subject.isRemembered()){ //Shiro会自动解密RememberMe Cookie里面的Principal，我们只需调用当前Subject的函数就行了
            String username = subject.getPrincipal().toString();
            User user = userService.get(username);
            if (user == null){
                throw new UnknownAccountException();
            }
            Session session = subject.getSession(false);
            session.setAttribute("user",user);
        }
        chain.doFilter(request,response);
    }
}
