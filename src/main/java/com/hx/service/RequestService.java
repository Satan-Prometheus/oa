package com.hx.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.hx.common.Flow;
import com.hx.common.FlowManager;
import com.hx.common.FlowStep;
import com.hx.dao.RequestDao;
import com.hx.dao.UserDao;
import com.hx.domain.Request;
import com.hx.domain.User;
import com.hx.view.objectview.RequestListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xh on 2015/2/27.
 */

@Repository
public class RequestService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RequestDao requestDao;

    @Autowired
    private FlowManager flowManager;

    public List<RequestListView> queryIndexList(User user) {

        List<FlowStep> relatedFlowSteps = user.getRelatedFlowSteps();

        if (relatedFlowSteps == null || relatedFlowSteps.size() == 0) {
            return null;
        }

        List<Request> requests = new ArrayList<Request>();
        for (FlowStep fs : relatedFlowSteps) {
            List<Request> r = requestDao.queryList(0, fs.flow.id, fs.order);
            if (r != null) {
                requests.addAll(r);
            }
        }

        List<String> userIds = new ArrayList<String>(requests.size());
        for (Request r : requests) userIds.add(r.getUserId());

        Map<String, User> userMap = userDao.queryMapByIds(userIds);

        List<RequestListView> viewList = new ArrayList<RequestListView>(requests.size());
        for (Request r : requests) {
            viewList.add(new RequestListView(r, userMap.get(r.getUserId())));
        }

        return viewList;
    }


    /**
     * 修改requestId审批状态流程
     * @param requestId
     * @param stepOrder 当前审批是针对流程中哪一步
     * @param operate 同意否 1:拒绝 2：同意
     * @return
     */
    public boolean operateRequest(int requestId, int stepOrder ,int operate) {

        List<Request> requests = requestDao.queryByIds(ImmutableList.of(requestId));
        if (requests == null || requests.size() == 0) {
            return false;
        }

        Request r = requests.get(0);
        String flowId = r.getFlowId();
        Flow flow = flowManager.getFlow(flowId);
        if (flow == null) return false;

        FlowStep nextStep = flow.findNextStep(stepOrder);

        Map<String, Object> queryMap = Maps.newHashMapWithExpectedSize(3);
        queryMap.put("request_id", requestId);
        queryMap.put("step_order", stepOrder);
        queryMap.put("approve", 0);



        if (nextStep == null) {
            // 如果这是最后一步

//            Map<String, Object> updateMap = Maps.newHashMapWithExpectedSize();
//            updateMap.put("", );
//
//            update set
        } else {
            // flow continue
        }


        return false;

    }
}
