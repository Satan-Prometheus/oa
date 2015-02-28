package com.hx.common;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by xh on 2015/2/27.
 */
public class FlowStep {

    @XmlAttribute(name = "order")
    public int order;

    @XmlAttribute(name = "level")
    public int level;

    @XmlAttribute(name = "department")
    public String department;

    public Flow flow;

    public Level lvl;

    private FlowStep nxt;

    public FlowStep() {}

    public FlowStep(FlowStep flowStep, String department) {
        this.order = flowStep.order;
        this.level = flowStep.level;
        this.department = department;
        this.flow = flowStep.flow;
        this.level = flowStep.level;
    }

    public FlowStep getNxt() {
        return nxt;
    }

    public void setNxt(FlowStep nxt) {
        this.nxt = nxt;
    }
}
