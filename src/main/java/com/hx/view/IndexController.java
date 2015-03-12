package com.hx.view;

import com.hx.domain.User;
import com.hx.view.tools.ForwardModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by xh on 2015/2/27.
 */

@Controller
@RequestMapping("/index")
public class IndexController extends CommonController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcome(HttpSession httpSession) {

        User user = user(httpSession);

        return new ModelAndView("welcome", new ForwardModel<String, Object>("user", user));
    }
}
