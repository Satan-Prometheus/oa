package com.hx.view;

import com.google.common.collect.Lists;
import com.hx.common.FlowManager;
import com.hx.domain.Request;
import com.hx.domain.User;
import com.hx.service.RequestService;
import com.hx.view.objectview.NewRequestInfo;
import com.hx.view.objectview.RequestListView;
import com.hx.view.tools.ForwardModel;
import com.hx.view.tools.JsonResultView;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by xh on 2015/2/28.
 */

@Controller
@RequestMapping("/request")
public class RequestController extends CommonController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private FlowManager flowManager;

    @ResponseBody
    @RequestMapping(value = "/approve/{request_id}/{step_order}/{approve}")
    public Object approve(@PathVariable("request_id") int requestId,
                          @PathVariable("step_order") int stepOrder,
                          @PathVariable("approve") int approve) {


        Request.Operate operate = Request.Operate.typeOf(approve);
        if (operate == null) {
            return new JsonResultView().errCode(1).errMsg("unknown approve type");
        }

        try {
            boolean b = requestService.operateRequest(requestId, stepOrder, operate);
            if (!b) {
                return new JsonResultView().errCode(1).errMsg("operate fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResultView().errCode(1).errMsg(e.getMessage());
        }

        return new JsonResultView();
    }

    @RequestMapping(value = "/create/pg")
    public ModelAndView newRequestPage(HttpSession httpSession) {

        Collection<String> requestTypeNames = flowManager.getRequestTypeNames();

        return new ModelAndView("newRequest", new ForwardModel<String, Object>("requestTypeNames", requestTypeNames)
                .append("user", user(httpSession))
                .append("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd")));
    }

    @ResponseBody
    @RequestMapping(value = "/create/do", method = RequestMethod.POST)
    public Object newRequest(HttpSession httpSession, NewRequestInfo requestInfo) {

        try {
            requestService.createNewRequest(user(httpSession), requestInfo);
            return new JsonResultView();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResultView().errCode(1).errMsg(e.getMessage());
        }
    }

    @RequestMapping(value = "/my/list/{choose}")
    public Object myRequestList(@PathVariable("choose") String choose,
                                HttpSession httpSession) {

        User user = user(httpSession);
        List<RequestListView> requestListViews = null;

        if (StringUtils.equals(choose, "ing")) {

            requestListViews = requestService.queryMyRequests(user, 0);
        } else if (StringUtils.equals(choose, "done")) {

            requestListViews = Lists.newArrayList();
            List<RequestListView> t;

            t = requestService.queryMyRequests(user, 1);
            if (t != null) requestListViews.addAll(t);

            t = requestService.queryMyRequests(user, 2);
            if (t != null) requestListViews.addAll(t);


        } else {
            requestListViews = requestService.queryMyRequests(user, -1);
        }


        return new ModelAndView("myRequestList", new ForwardModel<String, Object>("list", requestListViews));

    }
}
