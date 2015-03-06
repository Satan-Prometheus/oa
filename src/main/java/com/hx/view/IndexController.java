package com.hx.view;

import com.hx.annotation.LoginIgnore;
import com.hx.domain.Request;
import com.hx.domain.User;
import com.hx.service.RequestService;
import com.hx.view.objectview.RequestListView;
import com.hx.view.tools.ForwardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xh on 2015/2/27.
 */

@Controller
@RequestMapping("/index")
public class IndexController extends CommonController {

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcome(HttpSession httpSession) {

        User user = user(httpSession);

        return new ModelAndView("welcome", new ForwardModel<String, Object>("user", user));
    }

    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    public ModelAndView approvePage(HttpSession httpSession,
                                HttpServletResponse response) {

        //        List<RequestListView> requestListViews = requestService.queryIndexList((User)httpSession.getAttribute("user"));

        User user = user(httpSession);

        Request r = new Request();
        r.setRequestType("bingjia");
        r.setRequestDetailJson("{}");
        r.setApprove(0);
        r.setStepOrder(1);
        r.setId(32);


        List<RequestListView> requestListViewlist1 = new ArrayList<RequestListView>();

        requestListViewlist1.add(new RequestListView(r, user)) ;

        return new ModelAndView("approve", new ForwardModel("list", requestListViewlist1).append("string", "test"));
    }
    @LoginIgnore
    @RequestMapping(value = "/operate", method = RequestMethod.GET)
    public ModelAndView doLogin(@RequestParam(value = "id", required = true) String requestID,
                                @RequestParam(value = "action", required = true) String action,
                                HttpSession httpSession,
                                HttpServletResponse response) {

        System.out.println("debug:GET /operate?id=" + requestID + "&action=" + action + " in IndexController.java");

        User user = (User) httpSession.getAttribute("user");
        /*update sql here*/
        if (action.equals("agree")) {

        } else if (action.equals("reject")) {

        } else if (action.equals("detail")) {

        } else {
            ;//err here;
        }


        try {
            response.sendRedirect("/a/index/welcome");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
