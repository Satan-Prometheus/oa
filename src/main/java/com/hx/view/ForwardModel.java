package com.hx.view;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xh on 2015/2/27.
 */
public class ForwardModel<K, V> extends HashMap<K, V> {

    public ForwardModel(K k, V v) {
        append(k ,v);
    }

    public ForwardModel append(K k, V v) {
        super.put(k, v);
        return this;
    }

    public ForwardModel append(Map<K, V> map) {
        super.putAll(map);
        return this;
    }
}
