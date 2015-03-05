package com.hx.dao;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xh on 2015/2/27.
 */
public class SqlUtil {

    /**
     * select * from t_table where p in (?,?,?);
     * 生成n个问号
     */
    public static String selectInPlaceHolder(int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append("?,");
        }
        return sb.substring(0, sb.length() - 1).toString();
    }


    /**
     * update request set a=?,b=?,c=?
     *
     * @param query
     * @return
     */
    public static Pair<String, List<Object>> updateSetPlaceHolder(Map<String, Object> query) {

        StringBuilder sb = new StringBuilder();

        List<Object> param = new ArrayList<Object>();

        for (Map.Entry<String, Object> e : query.entrySet()) {
            sb.append("`").append(e.getKey()).append("`=?,");
            param.add(e.getValue());
        }

        return new ImmutablePair<String, List<Object>>(sb.substring(0, sb.length() - 1), param);
    }

    /**
     * update request set x where a=? and b=? and c=?
     *
     * @param query
     * @return
     */
    public static Pair<String, List<Object>> updateWherePlaceHolder(Map<String, Object> query) {

        StringBuilder sb = new StringBuilder();

        List<Object> param = new ArrayList<Object>();

        for (Map.Entry<String, Object> e : query.entrySet()) {

            if (sb.length() != 0) {
                sb.append(" and ");
            }

            sb.append("`").append(e.getKey()).append("`=?");

            param.add(e.getValue());
        }

        return new ImmutablePair<String, List<Object>>(sb.toString(), param);
    }


    public static class Condition {
        private String column;

        public enum Operate {

        }

        private String op;
    }

    public static class SimpleQueryBuilder {

    }
}