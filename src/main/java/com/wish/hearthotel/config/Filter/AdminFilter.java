package com.wish.hearthotel.config.Filter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("username")!=null) {
            String role = (String) session.getAttribute("role");
            if (role.contains("admin")) {
                return true;
            }else if(role.contains("student")){
                request.getRequestDispatcher("/student/getuser").forward(request,response);
                return false;
            }else if(role.contains("master")){
                request.getRequestDispatcher("/housemaster/todomain").forward(request,response);
                return false;
            }
        }
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }
}
