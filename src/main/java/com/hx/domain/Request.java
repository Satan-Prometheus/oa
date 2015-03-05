package com.hx.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Date;
import java.util.Map;

/**
 * Created by xh on 2015/2/27.
 */
public class Request {

    public enum Operate {
        agree(2), disagree(1);

        private Integer code;
        Operate(Integer code) {
            this.code = code;
        }

        public Integer code() { return this.code; }

        public static Operate typeOf(Integer code) {
            for (Operate o : Operate.values()) {
                if (o.code == code) {
                    return o;
                }
            }
            return null;
        }
    }


    private Integer id;

    private String userId;

    private String flowId;

    private Integer stepOrder;

    private String requestType;

    private String requestDetailJson;

    private Integer approve;

    private Date createTime;

    private Date lastUpdateTime;

    private Map<String, Object> requestDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(Integer stepOrder) {
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

    public Integer getApprove() {
        return approve;
    }

    public void setApprove(Integer approve) {
        this.approve = approve;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Map<String, Object> getRequestDetail() {
        if (this.requestDetail == null) {
            this.requestDetail = JSON.parseObject(this.requestDetailJson, new TypeReference<Map<String, Object>>() {});
        }
        return requestDetail;
    }

    public void setRequestDetail(Map<String, Object> requestDetail) {
        if (this.requestDetailJson == null) {
            this.requestDetailJson = JSON.toJSONString(requestDetail);
        }
        this.requestDetail = requestDetail;
    }
}
