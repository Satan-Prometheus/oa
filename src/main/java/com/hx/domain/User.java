package com.hx.domain;

import com.hx.common.Flow;
import com.hx.common.FlowManager;
import com.hx.common.FlowStep;
import com.hx.system.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by xh on 2015/2/27.
 */
public class User {

    private String id;

    private String name;

    private String password;

    private String department;

    private int level;

    private Date lastLoginTime;

    private List<FlowStep> relatedFlowSteps;

    public List<FlowStep> getRelatedFlowSteps() {
        return relatedFlowSteps;
    }

    public void initRelatedFlowSteps() {

        relatedFlowSteps = new ArrayList<FlowStep>();

        FlowManager flows = (FlowManager)SpringContextUtil.getBean("flows");
        for (Map.Entry<String, Flow> e : flows.getFlows().entrySet()) {
            for (FlowStep step : e.getValue().steps) {
                if (step.level == level) {

                    if (StringUtils.isBlank(step.department)) {
                        relatedFlowSteps.add(new FlowStep(step, this.department));
                    } else if (StringUtils.equals(step.department, this.department)) {
                        relatedFlowSteps.add(step);
                    }
                }
            }

        }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
