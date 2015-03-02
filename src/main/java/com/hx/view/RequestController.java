package com.hx.view;

import com.hx.common.FlowManager;
import com.hx.domain.Request;
import com.hx.domain.User;
import com.hx.service.RequestService;
import com.hx.view.objectview.NewRequestInfo;
import com.hx.view.tools.ForwardModel;
import com.hx.view.tools.JsonResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Created by xh on 2015/2/28.
 */

@Controller
@RequestMapping("/request")
public class RequestController {

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
            public ModelAndView newRequestPage() {

        Collection<String> requestTypeNames = flowManager.getRequestTypeNames();

        return new ModelAndView("newRequest", new ForwardModel<String, Object>("requestTypeNames", requestTypeNames));
    }


    @RequestMapping(value = "/create/do")
    public ModelAndView newRequest(HttpSession httpSession,
            @ModelAttribute("requestInfo") NewRequestInfo requestInfo) {


        requestService.createNewRequest((User)httpSession.getAttribute("user"), requestInfo);

        return null;
    }
}
