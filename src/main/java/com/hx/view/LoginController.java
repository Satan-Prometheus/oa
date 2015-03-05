package com.hx.view;

import com.hx.annotation.LoginIgnore;
import com.hx.dao.UserDao;
import com.hx.domain.User;
import com.hx.view.tools.ForwardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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
    public Object doLogin(@RequestParam(value = "userId", required = true) String userId,
                          @RequestParam(value = "password", required = true) String password,
                          HttpSession session,
                          RedirectAttributesModelMap modelMap) {


        User user = userDao.queryByIdPwd(userId, password);

        if (user == null) {
            modelMap.addFlashAttribute("a","aaa");
//            return "redirect:/a/login/pg";
            return new ModelAndView("login", new ForwardModel("errMsg", "用户名或密码错误"));
        }

        user.initRelatedFlowSteps();
        session.setAttribute("user", user);

        return "redirect:/a/index/welcome";
    }


    @LoginIgnore
    @RequestMapping(value = "/pg", method = RequestMethod.GET)
    public ModelAndView loginPage() {

        return new ModelAndView("login");
    }

}
