package com.hx.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.hx.common.Flow;
import com.hx.common.FlowManager;
import com.hx.common.FlowStep;
import com.hx.common.RequestType;
import com.hx.dao.RequestDao;
import com.hx.dao.UserDao;
import com.hx.domain.Request;
import com.hx.domain.User;
import com.hx.view.objectview.NewRequestInfo;
import com.hx.view.objectview.RequestListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

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
    public boolean operateRequest(int requestId, int stepOrder, Request.Operate operate) {

        List<Request> requests = requestDao.queryByIds(ImmutableList.of(requestId));
        if (requests == null || requests.size() == 0) {
            return false;
        }

        Request r = requests.get(0);
        String flowId = r.getFlowId();
        Flow flow = flowManager.getFlow(flowId);
        if (flow == null) return false;


        Map<String, Object> queryMap = Maps.newHashMapWithExpectedSize(3);
        queryMap.put("id", requestId);
        queryMap.put("step_order", stepOrder);
        queryMap.put("approve", 0);

        Map<String, Object> updateMap = Maps.newHashMap();
        updateMap.put("last_update_time", new Date());

        FlowStep nextStep = flow.findNextStep(stepOrder);
        // last step
        if (nextStep == null) {
            updateMap.put("approve", operate.code());
        } else {
            // flow continue

            if (operate == Request.Operate.disagree) { // reject
                updateMap.put("approve", operate.code());
            } else {    // go to next step
                updateMap.put("step_order", nextStep.order);
            }
        }

        return requestDao.updateRequest(queryMap, updateMap) > 0;
    }


    public boolean createNewRequest(User user, NewRequestInfo requestInfo) {
        String requestType = requestInfo.getRequestType();
        if (requestType == null) {
            throw new IllegalArgumentException("request type should not be null");
        }

        Collection<RequestType> requestTypes = flowManager.getRequestTypeByName(requestType);
        if (requestTypes == null || requestTypes.isEmpty()) {
            throw new IllegalArgumentException("unknown request type name");
        }



        // TODO
        return false;
    }
}
