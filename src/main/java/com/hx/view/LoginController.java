package com.hx.view;

import com.hx.annotation.LoginIgnore;
import com.hx.dao.TestDao;
import com.hx.dao.UserDao;
import com.hx.domain.User;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2014/12/1.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @LoginIgnore
    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public ModelAndView doLogin(@RequestParam(value = "userId", required = true) String userId,
                          @RequestParam(value = "password", required = true) String password,
                          HttpSession session,
                          HttpServletResponse response) {


        User user = userDao.queryByIdPwd(userId, password);

        if (user == null) {
            // ’À∫≈ªÚ’ﬂ√‹¬Î¥ÌŒÛ µ«¬º ß∞‹
            return new ModelAndView("login", new ForwardModel("errMsg", "’À∫≈ªÚ’ﬂ√‹¬Î¥ÌŒÛ"));
        }

        session.setAttribute("user", user);

        try {
            response.sendRedirect("/a/index/welcome");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return new ModelAndView("index");
        return null;
    }


    @LoginIgnore
    @RequestMapping(value = "/pg", method = RequestMethod.GET)
    public ModelAndView loginPage() {

        return new ModelAndView("login");
    }

}
