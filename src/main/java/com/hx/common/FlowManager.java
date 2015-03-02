package com.hx.common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.Resources;
import com.hx.domain.Request;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xh on 2015/2/27.
 */
public class FlowManager {

    private Map<String, Flow> flowHolder = new HashMap<String, Flow>();

    private Map<Integer, Level> levelHolder = new HashMap<Integer, Level>();

    private Multimap<String, RequestType> requestTypeHolder = HashMultimap.create();

    public FlowManager() {

        try {
            JAXBContext jc = JAXBContext.newInstance(Flows.class);
            Unmarshaller u = jc.createUnmarshaller();

            Flows flows = (Flows) u.unmarshal(new File(Resources.getResource("flow.xml").getFile()));


            for (Level l : flows.level) {
                levelHolder.put(l.level, l);
            }

            for (Flow f : flows.flows) {
                f.buildRelate();

                flowHolder.put(f.id, f);
                for (FlowStep fs : f.steps) {
                    fs.flow = f;
                    fs.lvl = levelHolder.get(fs.level);
                }

                for (RequestType rt : f.requestTypes) {
                    rt.flow = f;
                    requestTypeHolder.put(rt.type, rt);
                }
            }



        } catch (JAXBException e) {
            throw new RuntimeException("init from flow.xml fail,", e);
        }
    }

    public Collection<String> getRequestTypeNames() {
        return this.requestTypeHolder.keySet();
    }

    public Collection<RequestType> getRequestTypeByName(String name) {
        return this.requestTypeHolder.get(name);
    }

    public Map<String, Flow> getFlows() {
        return this.flowHolder;
    }

    public Flow getFlow(String flowId) {
        return this.flowHolder.get(flowId);
    }

    @XmlRootElement(name = "flows")
    public static class Flows {

        @XmlElement(name = "flow")
        public Flow[] flows;

        @XmlElement(name = "level")
        public Level[] level;
    }


}
