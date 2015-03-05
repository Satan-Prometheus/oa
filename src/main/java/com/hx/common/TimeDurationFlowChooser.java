package com.hx.common;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by xh on 2015/3/5.
 */
public class TimeDurationFlowChooser extends FlowChooser {

    private TimeDuration timeDuration;

    public TimeDurationFlowChooser(String requestType, int day, int hour) {
        super(requestType);
        this.timeDuration = new TimeDuration();
        this.timeDuration.day = day;
        this.timeDuration.hour = hour;
    }

    @Override
    public boolean accept(RequestType requestType) {
        if (requestType == null) return false;
        Pair<TimeDuration, TimeDuration> duration = requestType.getDuration();
        if (duration == null) return false;

        if (timeDuration.compareTo(duration.getLeft()) > 0 && timeDuration.compareTo(duration.getRight()) <= 0) {
            return true;
        }
        return false;
    }
}
