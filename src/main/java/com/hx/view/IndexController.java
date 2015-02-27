package com.hx.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xh on 2015/2/27.
 */

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcome() {



        return new ModelAndView("index");
    }
}
