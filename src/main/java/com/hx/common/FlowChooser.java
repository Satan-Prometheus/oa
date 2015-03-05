package com.hx.common;

/**
 * Created by xh on 2015/3/5.
 */
public abstract class FlowChooser {

    protected String requestType;

    public FlowChooser(String requestType) {
        this.requestType = requestType;
    }

    public abstract boolean accept(RequestType requestType);

}
