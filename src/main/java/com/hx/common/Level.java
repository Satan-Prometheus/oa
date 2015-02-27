package com.hx.common;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by xh on 2015/2/27.
 */
public class Level {

    @XmlAttribute(name = "lvl")
    public int level;

    @XmlAttribute(name = "desc")
    public String desc;

}
