package com.hx.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by xh on 2015/3/1.
 */


public class RequestType {

    @XmlAttribute(name = "type")
    public String type;

    @XmlAttribute(name = "day-range")
    public String dayRange;

    private Pair<TimeDuration, TimeDuration> duration;

    public Flow flow;

    public RequestType() {
    }


    public Pair<TimeDuration, TimeDuration> getDuration() {
        if (StringUtils.isBlank(dayRange)) {
            return null;
        }

        if (duration != null) {
            return duration;
        }

        duration = parseDayHourRange();
        return duration;
    }


    private Pair<TimeDuration, TimeDuration> parseDayHourRange() {
        final String range = this.dayRange;

        String[] split = StringUtils.split(range, '-');

        if (split.length != 2) return null;

        TimeDuration ftd = parseDayHour(split[0]);
        if (ftd == null) return null;

        TimeDuration ttd;
        if (StringUtils.isBlank(split[1])) {
            ttd = new TimeDuration();
            ttd.day = Integer.MAX_VALUE;
            ttd.hour = 0;
        } else {
            ttd = parseDayHour(split[1]);
            if (ttd == null) return null;
        }

        return new ImmutablePair<TimeDuration, TimeDuration>(ftd, ttd);
    }

    private TimeDuration parseDayHour(String s) {
        int dpos = s.indexOf('d');

        int day = 0;
        if (dpos > 0) {
            try {
                day = Integer.valueOf(s.substring(0, dpos));
            } catch (Exception e) {
                return null;
            }
        }

        int hpos = s.indexOf('h', dpos + 1);
        int hour = 0;
        if (hpos > 0) {
            try {
                hour = Integer.valueOf(s.substring(dpos+1, hpos));
            } catch (Exception e) {
                return null;
            }
        }

        TimeDuration td = new TimeDuration();
        td.day = day;
        td.hour = hour;

        if (day < 0) return null;
        if (hour < 0 || hour >= 24) return null;

        return td;
    }


    public static void main(String[] args) {
        RequestType rt = new RequestType();
        rt.dayRange = "0d4h-1d3h";

        Pair<TimeDuration, TimeDuration> duration1 = rt.getDuration();


        System.out.println(duration1.getKey().day);
        System.out.println(duration1.getKey().hour);
        System.out.println(duration1.getValue().day);
        System.out.println(duration1.getValue().hour);
    }
}
