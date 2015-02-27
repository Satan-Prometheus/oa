package com.hx.common;

import com.google.common.io.Resources;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xh on 2015/2/27.
 */
public class FlowManager {

    private Map<String, Flow> flowHolder = new HashMap<String, Flow>();

    private Map<Integer, Level> levelHolder = new HashMap<Integer, Level>();


    public FlowManager() {

        try {
            JAXBContext jc = JAXBContext.newInstance(Flows.class);
            Unmarshaller u = jc.createUnmarshaller();

            Flows flows = (Flows) u.unmarshal(new File(Resources.getResource("flow.xml").getFile()));


            for (Level l : flows.level) {
                levelHolder.put(l.level, l);
            }

            for (Flow f : flows.flows) {
                flowHolder.put(f.id, f);

                for (FlowStep fs : f.steps) {
                    fs.flow = f;
                    fs.lvl = levelHolder.get(fs.level);
                }
            }



        } catch (JAXBException e) {
            throw new RuntimeException("init from flow.xml fail,", e);
        }
    }


    @XmlRootElement(name = "flows")
    public static class Flows {

        @XmlElement(name = "flow")
        public Flow[] flows;

        @XmlElement(name = "level")
        public Level[] level;
    }


}
