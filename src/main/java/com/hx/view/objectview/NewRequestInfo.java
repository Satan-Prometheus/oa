package com.hx.view.objectview;

import com.hx.common.FlowChooser;
import com.hx.common.TimeDurationFlowChooser;

/**
 * Created by xh on 2015/3/3.
 */
public class NewRequestInfo {

    private String requestType;

    private Integer day;

    private Integer hour;

    private String requestReason;

    private String from;

    private String to;

    public FlowChooser createFlowChooser() {

        if (day != null || hour != null) {

            return new TimeDurationFlowChooser(requestType, day == null ? 0 : day, hour == null ? 0 : hour);
        }

        return null;
    }

    public String getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
