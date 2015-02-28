package com.hx.common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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

    private Map<Integer, FlowStep> stepHolder;

    public FlowStep findNextStep(int curOrder) {

        FlowStep cur = stepHolder.get(curOrder);
        if (cur == null) {
            throw new IllegalStateException("curOrder:" + curOrder + " not exist");
        }

        return cur.getNxt();
    }

    public void buildRelate() {

        Arrays.sort(steps, new Comparator<FlowStep>() {
            @Override
            public int compare(FlowStep o1, FlowStep o2) {
                return o1.order - o2.order;
            }
        });

        stepHolder = new HashMap<Integer, FlowStep>();

        for (int i = 0; i < steps.length; i++) {
            stepHolder.put(steps[i].order, steps[i]);
            if (i+1 == steps.length) {
                steps[i].setNxt(null);
            } else {
                steps[i].setNxt(steps[i+1]);
            }
        }
    }
}
