package com.dayo.filter;

import com.dayo.pojo.User;
import com.dayo.pojo.UserStates;
import com.dayo.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author DayoWong on 2018/9/27
 */
public class URLPathMatchingFilter extends PathMatchingFilter {

    @Autowired
    PermissionService permissionService;
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String requestURI = getPathWithinApplication(request);
        System.out.println("requestURI:"+requestURI);
        Subject subject = SecurityUtils.getSubject();

        //没登陆则跳转
        if(!subject.isAuthenticated()){
            WebUtils.issueRedirect(request,response,"/login");
            return  false;
        }

        //判断账户是否激活、封号
        Boolean checkUserStates = checkUserStates(request, response, subject);
        if (checkUserStates!= null)
            return checkUserStates;

        //判断requestURI是否需要权限维护，无则放行
        boolean needInterceptor = permissionService.needInterceptor(requestURI);
        if(!needInterceptor){
            return true;
        }else {
            boolean hasPermission = false;
            String userName = subject.getPrincipal().toString();
            Set<String> permissionUrls = permissionService.listPermissionURLs(userName);
            for (String url : permissionUrls) {
                // 当前用户有这个权限，放行
                if (url.equals(requestURI)) {
                    hasPermission = true;
                    break;
                }
            }
            if(hasPermission){
                return true;
            }else{
                //用户没有当前请求权限，跳转
                UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");
                subject.getSession().setAttribute("ex", ex);
                WebUtils.issueRedirect(request, response, "/unauthorized");
                return false;
            }
        }
    }

    private Boolean checkUserStates(ServletRequest request, ServletResponse response, Subject subject) throws IOException {
        User user = (User) subject.getSession().getAttribute("user");
        switch (user.getState()){
            case UserStates.ACTIVATION:
                break;
            case UserStates.INACTIVATION:
                subject.getSession().setAttribute("msg","账号未激活，请检前往注册页面激活账号");
                WebUtils.issueRedirect(request,response,"/result");
                return false;
            case UserStates.BANNED:
                subject.getSession().setAttribute("msg","已被封号");
                WebUtils.issueRedirect(request,response,"/result");
                return false;
            default:
                break;
        }
        return null;
    }
}
