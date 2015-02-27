package com.hx.common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by xh on 2015/2/27.
 */
public class Flow {

    @XmlAttribute(name = "id")
    public String id;

    @XmlAttribute(name = "desc")
    public String desc;

    @XmlElement(name = "step")
    public FlowStep[] steps;

    public Flow(String id, String desc, FlowStep[] steps) {
        this.id = id;
        this.desc = desc;
        this.steps = steps;
    }
}
