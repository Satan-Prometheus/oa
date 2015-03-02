package com.hx.common;

/**
 * Created by xh on 2015/3/1.
 */
public class TimeDuration implements Comparable<TimeDuration> {

    public int day;

    public int hour;

    @Override
    public int compareTo(TimeDuration o) {
        if (this.day == o.day) {
            return this.hour - o.hour;
        }
        return this.day - o.day;
    }
}
