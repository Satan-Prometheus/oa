package com.hx.view;

import com.hx.annotation.LoginIgnore;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xh on 2015/2/26.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            LoginIgnore ignored = method.getMethodAnnotation(LoginIgnore.class);


            if (ignored != null) {
                return true;
            }
        }

        HttpSession session = request.getSession();

        Object user_id = session.getAttribute("user");

        if (user_id == null) {

            response.sendRedirect("/a/login");
            return false;
        }

        return true;
    }

}
