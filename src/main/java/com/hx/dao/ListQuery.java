package com.hx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xh on 2015/3/4.
 */
public class ListQuery extends HashMap<Object, Object> {

    public enum Order {
        AESC, DESC
    }



    private Integer offset;

    private Integer limit;

    private Order order;

    private List<Sort> sorts;

    public ListQuery(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;

        super.put("_offset", offset);
        super.put("_limit", limit);
    }

    public ListQuery() {}


    public ListQuery condition(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public ListQuery orderBy(String key, Order order) {
        if (sorts == null) {
            sorts = new ArrayList<Sort>();
            super.put("_sorts", sorts);
        }
        sorts.add(new Sort(key, order.name()));
        return this;
    }


    private static class Sort {
        private String name;
        private String order;

        private Sort(String name, String order) {
            this.name = name;
            this.order = order;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
