package com.hx.service;

import com.hx.common.*;
import com.hx.dao.RequestExample;
import com.hx.dao.RequestMapper;
import com.hx.dao.UserMapper;
import com.hx.domain.Request;
import com.hx.domain.User;
import com.hx.view.objectview.NewRequestInfo;
import com.hx.view.objectview.RequestListView;
import org.apache.commons.lang3.time.DateFormatUtils;
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

    public List<RequestListView> queryToOperateRequests(User user) {

        List<FlowStep> relatedFlowSteps = user.getRelatedFlowSteps();

        if (relatedFlowSteps == null || relatedFlowSteps.size() == 0) {
            return null;
        }

        List<Request> requests = new ArrayList<Request>();
        RequestExample re = new RequestExample();

        for (FlowStep fs : relatedFlowSteps) {
            re.clear();
            re.createCriteria().andApproveEqualTo(0)
                    .andFlowIdEqualTo(fs.flow.id)
                    .andStepOrderEqualTo(fs.order);
            List<Request> r = requestMapper.selectByExample(re);
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

        Request r = requestMapper.selectByPrimaryKey(requestId);
        if (r == null) return false;
        String flowId = r.getFlowId();
        Flow flow = flowManager.getFlow(flowId);
        if (flow == null) return false;

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

        RequestExample re = new RequestExample();
        re.createCriteria()
                .andIdEqualTo(requestId)
                .andStepOrderEqualTo(stepOrder)
                .andApproveEqualTo(0);
        return requestMapper.updateByExampleSelective(request, re) > 0;
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


        try {
            Date now = new Date();

            Request request = new Request();
            request.setUserId(user.getId());
            request.setFlowId(flow.id);
            request.setStepOrder(flow.findNextStep(-1).order);
            request.setRequestType(requestType);

            request.setApprove(0);
            request.setCreateTime(now);
            request.setLastUpdateTime(now);

            Map<String, Object> detailMap = new HashMap<String, Object>();
            detailMap.put("day", requestInfo.getDay());
            detailMap.put("hour", requestInfo.getHour());
            detailMap.put("desc", requestInfo.getRequestReason());
            detailMap.put("from", DateFormatUtils.format(new Date(Long.valueOf(requestInfo.getFrom())), "yyyy-MM-dd HH:mm:ss"));
            detailMap.put("to", DateFormatUtils.format(new Date(Long.valueOf(requestInfo.getTo())), "yyyy-MM-dd HH:mm:ss"));
            request.setRequestDetail(detailMap);

            requestMapper.insert(request);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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

        RequestExample re = new RequestExample();
        re.setOrderByClause("last_update_time");
        RequestExample.Criteria criteria = re.createCriteria().andUserIdEqualTo(user.getId());

        if (approve != -1) criteria.andApproveEqualTo(approve);

        List<Request> requests = requestMapper.selectByExample(re);

        List<RequestListView> viewList = new ArrayList<RequestListView>(requests.size());
        for (Request r : requests) {
            viewList.add(new RequestListView(r, user));
        }

        return viewList;
    }
}
