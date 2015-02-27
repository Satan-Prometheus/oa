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


    public FlowStep findNextStep(int curOrder) {

        FlowStep r = null;

        for (FlowStep fs : steps) {
            if (curOrder >= fs.order) {
                continue;
            }
            if (r == null || r.order > fs.order) r = fs;
        }
        return r;
    }
}
