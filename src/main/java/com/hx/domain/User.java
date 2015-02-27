package com.hx.domain;

import com.hx.common.FlowStep;

import java.util.Date;
import java.util.List;

/**
 * Created by xh on 2015/2/27.
 */
public class User {

    private String id;

    private String name;

    private String password;

    private String department;

    private Date lastLoginTime;

    private List<FlowStep> relatedFlowSteps;

    public List<FlowStep> getRelatedFlowSteps() {
        return relatedFlowSteps;
    }

    public void initRelatedFlowSteps() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
