package com.hx.service;

import com.google.common.collect.ImmutableList;
import com.hx.common.*;
import com.hx.dao.*;
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
    private RequestMapper requestMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FlowManager flowManager;

    public List<RequestListView> queryIndexList(User user) {

        List<FlowStep> relatedFlowSteps = user.getRelatedFlowSteps();

        if (relatedFlowSteps == null || relatedFlowSteps.size() == 0) {
            return null;
        }

        List<Request> requests = new ArrayList<Request>();
        ListQuery query = new ListQuery().condition("approve", 0);


        for (FlowStep fs : relatedFlowSteps) {
            query.condition("flowId", fs.flow.id).condition("stepOrder", fs.order);
            List<Request> r = requestMapper.selectRequests(query);
            if (r != null) {
                requests.addAll(r);
            }
        }

        List<String> userIds = new ArrayList<String>(requests.size());
        for (Request r : requests) userIds.add(r.getUserId());

        Map<String, User> userMap = User.idMapper(userMapper.selectByIds(userIds));

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

        List<Request> requests = requestMapper.selectByIds(ImmutableList.of(requestId));
        if (requests == null || requests.size() == 0) {
            return false;
        }

        Request r = requests.get(0);
        String flowId = r.getFlowId();
        Flow flow = flowManager.getFlow(flowId);
        if (flow == null) return false;

        ListQuery listQuery = new ListQuery()
                .condition("id", requestId)
                .condition("stepOrder", stepOrder)
                .condition("approve", 0);

        Request request = new Request();

        FlowStep nextStep = flow.findNextStep(stepOrder);
        // last step
        if (nextStep == null) {
            request.setApprove(operate.code());
        } else {
            // flow continue

            if (operate == Request.Operate.disagree) { // reject
                request.setApprove(operate.code());
            } else {    // go to next step
                request.setStepOrder(nextStep.order);
            }
        }

        return requestMapper.updateRequest(listQuery, request) > 0;
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

        FlowChooser flowChooser = requestInfo.createFlowChooser();

        Flow flow = flowManager.chooseFlow(flowChooser);
        if (flow == null) {
            throw new IllegalStateException("can't match flow by submitted info");
        }


        Date now = new Date();

        Request request = new Request();
        request.setUserId(user.getId());
        request.setFlowId(flow.id);
        request.setStepOrder(flow.findNextStep(-1).order);
        request.setRequestType(requestType);
        request.setRequestDetail(new HashMap()); //TODO
        request.setApprove(0);
        request.setCreateTime(now);
        request.setLastUpdateTime(now);

        requestMapper.insertRequest(request);

        return false;
    }

    /**
     *
     * @param user
     * @param approve
     * @return
     */
    public List<RequestListView> queryMyRequests(User user, int approve) {

        if (approve < 0 || approve > 2) {
            // all requests
            approve = -1;
        }

        ListQuery query = new ListQuery();
        if (approve != -1) query.condition("approve", approve);

        query.condition("userId", user.getId());
        query.orderBy("lastUpdateTime", ListQuery.Order.DESC);

        List<Request> requests = requestMapper.selectRequests(query);

        List<RequestListView> viewList = new ArrayList<RequestListView>(requests.size());
        for (Request r : requests) {
            viewList.add(new RequestListView(r, user));
        }

        return viewList;
    }
}
