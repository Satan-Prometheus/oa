package com.hx.dao;

/**
 * Created by xh on 2015/2/27.
 */
public class SqlUtil {

    public static String placeHolder(int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append("?,");
        }
        return sb.substring(0, sb.length() - 1).toString();
    }
}
