package com.hx.service;

import com.hx.common.*;
import com.hx.dao.*;
import com.hx.domain.Request;
import com.hx.domain.RequestOpLog;
import com.hx.domain.User;
import com.hx.view.objectview.NewRequestInfo;
import com.hx.view.objectview.RequestListView;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by xh on 2015/2/27.
 */

@Repository
public class RequestService {

    @Autowired
    private RequestOpLogMapper requestOpLogMapper;

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

        List<RequestListView> viewList = new ArrayList<RequestListView>(requests.size());

        if (requests.size() > 0) {
            Map<String, User> userMap = queryRequestRelatedUsers(requests);
            for (Request r : requests) {
                viewList.add(new RequestListView(r, userMap.get(r.getUserId()), userMap.get(r.getLastOperatorId())));
            }
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
    @Transactional
    public boolean operateRequest(User user, int requestId, int stepOrder, Request.Operate operate, String remark) {

        Request r = requestMapper.selectByPrimaryKey(requestId);
        if (r == null) return false;
        String flowId = r.getFlowId();
        Flow flow = flowManager.getFlow(flowId);
        if (flow == null) return false;

        Date now = new Date();

        Request request = new Request();
        request.setLastOperatorId(user.getId());
        request.setLastUpdateTime(now);

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

        RequestOpLog opLog = new RequestOpLog();
        opLog.setRequestId(requestId);
        opLog.setCreateTime(now);
        opLog.setRemark(remark);
        opLog.setOpType(operate.code());
        opLog.setUserId(user.getId());
        requestOpLogMapper.insert(opLog);

        RequestExample re = new RequestExample();
        re.createCriteria()
                .andIdEqualTo(requestId)
                .andStepOrderEqualTo(stepOrder)
                .andApproveEqualTo(0);
        return requestMapper.updateByExampleSelective(request, re) > 0;
    }

    @Transactional
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

        FlowStep step = flow.findFirstStep(user.getLevel());
        if (step == null) {
            throw new IllegalStateException("can't find flow step");
        }


        try {
            Date now = new Date();

            Request request = new Request();
            request.setUserId(user.getId());
            request.setFlowId(flow.id);
            request.setStepOrder(step.order);
            request.setRequestType(requestType);

            request.setApprove(0);
            request.setCreateTime(now);
            request.setLastUpdateTime(now);
            request.setLastOperatorId(user.getId());

            Map<String, Object> detailMap = new HashMap<String, Object>();
            detailMap.put("day", requestInfo.getDay());
            detailMap.put("hour", requestInfo.getHour());
            detailMap.put("desc", requestInfo.getRequestReason());
            detailMap.put("from", DateFormatUtils.format(new Date(Long.valueOf(requestInfo.getFrom())), "yyyy-MM-dd HH:mm:ss"));
            detailMap.put("to", DateFormatUtils.format(new Date(Long.valueOf(requestInfo.getTo())), "yyyy-MM-dd HH:mm:ss"));
            request.setRequestDetail(detailMap);

            int requestId = requestMapper.insert(request);

            RequestOpLog opLog = new RequestOpLog();
            opLog.setRequestId(requestId);
            opLog.setCreateTime(now);
            opLog.setRemark("创建申请");
            opLog.setOpType(0);
            opLog.setUserId(user.getId());
            requestOpLogMapper.insert(opLog);

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
        re.setOrderByClause("last_update_time desc");
        RequestExample.Criteria criteria = re.createCriteria().andUserIdEqualTo(user.getId());

        if (approve != -1) criteria.andApproveEqualTo(approve);

        List<Request> requests = requestMapper.selectByExample(re);

        List<RequestListView> viewList = new ArrayList<RequestListView>(requests.size());
        if (requests.size() > 0) {
            Map<String, User> userMap = queryRequestRelatedUsers(requests);
            for (Request r : requests) {
                viewList.add(new RequestListView(r, user, userMap.get(r.getLastOperatorId())));
            }
        }
        return viewList;
    }

    private Map<String, User> queryRequestRelatedUsers(List<Request> requests) {
        List<String> userIds = new ArrayList<String>(requests.size());
        for (Request r : requests) userIds.add(r.getUserId());

        return User.idMapper(userMapper.selectByIds(userIds));
    }
}
