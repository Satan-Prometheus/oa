package com.hx.view;

import com.hx.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2014/12/1.
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestDao testDao;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public Object test1(@RequestParam(value = "sEcho", required = false) Integer sEcho) {


        return new OO();
    }

    @RequestMapping(value = "/dao", method = RequestMethod.GET)
    @ResponseBody
    public Object testDao() {


        OO o = new OO();
        o.setA(testDao.testQuery());

        return o;
    }

    public static class OO {
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
