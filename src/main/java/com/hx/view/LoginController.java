package com.hx.view;

import com.hx.annotation.LoginIgnore;
import com.hx.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2014/12/1.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private TestDao testDao;

    @LoginIgnore
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Object doLogin(@RequestParam(value = "userId", required = true) String userId,
                          @RequestParam(value = "password", required = true) String password) {


        System.out.println(userId);
        System.out.println(password);

        return new JsonResultView();
    }

}
