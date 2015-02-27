package com.hx.view;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xh on 2015/2/26.
 */
public class JsonResultView {

    private int errCode = 0;

    private String errMsg = "";

    private Map<String, Object> data = null;

    public JsonResultView errCode(int errCode) {
        this.errCode = errCode;
        return this;
    }

    public JsonResultView errMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public JsonResultView addData(String k, Object v) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }

        data.put(k, v);
        return this;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
