package com.hx.view;

import com.hx.annotation.LoginIgnore;
import com.hx.dao.UserMapper;
import com.hx.domain.User;
import com.hx.view.tools.ForwardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/12/1.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @LoginIgnore
    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public Object doLogin(@RequestParam(value = "userId", required = true) String userId,
                          @RequestParam(value = "password", required = true) String password,
                          HttpSession session) {


        List<User> users = userMapper.selectByIdPwd(userId, password);

        if (CollectionUtils.isEmpty(users)) {
//            return "redirect:/a/login/pg";
//            return new ModelAndView("login", new ForwardModel("errMsg", "用户名或密码错误"));

            return new ModelAndView(new RedirectView("/a/login/pg"), new ForwardModel<String, Object>("errMsg", "1"));
        }

        User user = users.get(0);
        user.initRelatedFlowSteps();
        session.setAttribute("user", user);

        User forUpdate = new User();
        forUpdate.setId(user.getId());
        forUpdate.setLastLoginTime(new Date());

        userMapper.update(forUpdate);

        return "redirect:/a/index/welcome";
    }


    @LoginIgnore
    @RequestMapping(value = "/pg", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "errMsg", required = false) String errMsg) {
        ModelAndView mv = new ModelAndView("login");
        if (errMsg != null) mv.addObject("errMsg", errMsg);
        return mv;
    }

}
