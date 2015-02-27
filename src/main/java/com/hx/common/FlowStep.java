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

    public FlowStep(int order, int level, String department) {
        this.order = order;
        this.level = level;
        this.department = department;
    }
}
