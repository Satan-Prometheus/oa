package com.hx.view;

import com.hx.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by xh on 2015/3/5.
 */
public class CommonController {

    protected User user(HttpSession httpSession) {
        return (User)httpSession.getAttribute("user");
    }
}
