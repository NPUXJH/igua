package com.wish.hearthotel.config.Filter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StudentFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("username")!=null) {
            String role = (String) session.getAttribute("role");
            if (role.contains("student")) {
                return true;
            }
            else if(role.contains("master")){
                request.getRequestDispatcher("/housemaster/todomain").forward(request,response);
                return false;
            }
            else if(role.contains("admin")){
                request.getRequestDispatcher("/administrator/todomain").forward(request,response);
                return false;
            }
        }
        request.setAttribute("error","用户名或密码错误或者没有权限，请先登录");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }
}
