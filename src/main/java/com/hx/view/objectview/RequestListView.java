package com.hx.view.objectview;

import com.hx.common.FlowStep;
import com.hx.domain.Request;
import com.hx.domain.User;
import java.util.Map;

import java.util.Date;
import java.util.List;

/**
 * Created by xh on 2015/2/27.
 */
public class RequestListView {
/*me*/
    private String userName;

    private String password;

    private String department;

    private int level;

    private Date lastLoginTime;

    private List<FlowStep> relatedFlowSteps;
/*me end*/

/*request */
    private int requestId;

    private String userId;

    private String flowId;

    private int stepOrder;

    private String requestType;

    private String requestDetailJson;

    private int approve;

    private Date createTime;

    private Date lastUpdateTime;

    private Map<String, Object> requestDetail;



    private String requestDetail_AskForLeave;

    private String lastOperatorName;
/*request */

    public RequestListView() {}

    public RequestListView(Request r, User me, User lastOperator) {

        this.userName = me.getName();
        this.password = me.getPassword();
        this.department = me.getDepartment();
        this.level = me.getLevel();
        this.lastLoginTime = me.getLastLoginTime();
        this.relatedFlowSteps = me.getRelatedFlowSteps();

        this.requestId = r.getId();
        this.userId = r.getUserId();
        this.flowId = r.getFlowId();
        this.stepOrder = r.getStepOrder();
        this.requestType = r.getRequestType();
        this.requestDetailJson = r.getRequestDetailJson();
        this.approve = r.getApprove();
        this.createTime = r.getCreateTime();
        this.lastUpdateTime = r.getLastUpdateTime();
        this.requestDetail = r.getRequestDetail();

        this.requestDetail_AskForLeave = "从"+requestDetail.get("from")+"到"+requestDetail.get("to")+"请"+requestDetail.get("day")+"天"+requestDetail.get("hour")
                                                +", 请假理由:"+requestDetail.get("desc");

        if (lastOperator != null) {
            this.lastOperatorName = lastOperator.getName();
        }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public List<FlowStep> getRelatedFlowSteps() {
        return relatedFlowSteps;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFlowId() {
        return flowId;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public int getApprove() {
        return approve;
    }

    public String getRequestDetailJson() {
        return requestDetailJson;
    }

    public String getRequestType() {
        return requestType;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public Map<String, Object> getRequestDetail() {
        return requestDetail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastOperatorName() {
        return lastOperatorName;
    }

    public void setLastOperatorName(String lastOperatorName) {
        this.lastOperatorName = lastOperatorName;
    }
    public String getRequestDetail_AskForLeave() {
        return requestDetail_AskForLeave;
    }
}
