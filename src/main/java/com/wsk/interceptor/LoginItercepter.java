package com.wsk.interceptor;

import com.wsk.pojo.UserInformation;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginItercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {
        System.out.println("访问路径："+httpServletRequest.getServletPath());
        UserInformation userInformation=(UserInformation) httpServletRequest.getSession().getAttribute("userInformation");
        if (userInformation==null){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write("<script type='text/javascript'>alert('你没有登陆！,将跳转到登录页');"
                    + "window.top.location.href='"+httpServletRequest.getContextPath()+"/login.do';"
                    + "</script>");
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
