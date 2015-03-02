package com.hx.domain;

import java.util.Date;
import java.util.Map;

/**
 * Created by xh on 2015/2/27.
 */
public class Request {

    public enum Operate {
        agree(2), disagree(1);

        private int code;
        Operate(int code) {
            this.code = code;
        }

        public int code() { return this.code; }

        public static Operate typeOf(int code) {
            for (Operate o : Operate.values()) {
                if (o.code == code) {
                    return o;
                }
            }
            return null;
        }
    }


    private int id;

    private String userId;

    private String flowId;

    private int stepOrder;

    private String requestType;

    private String requestDetailJson;

    private int approve;

    private Date lastUpdateTime;

    private Map<String, Object> requestDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestDetailJson() {
        return requestDetailJson;
    }

    public void setRequestDetailJson(String requestDetailJson) {
        this.requestDetailJson = requestDetailJson;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Map<String, Object> getRequestDetail() {
        return requestDetail;
    }

    public void setRequestDetail(Map<String, Object> requestDetail) {
        this.requestDetail = requestDetail;
    }
}
