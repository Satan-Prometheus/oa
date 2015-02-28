package com.hx.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hx.domain.Request;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xh on 2015/2/27.
 */

@Repository
public class RequestDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Request> queryList(int status, String flowId, int stepOrder) {

        final String sql = String.format("select %s from t_request where flow_id=? and step_order=? and approve=?",
                RequestMapper.RESULT_KEY_LIST);

        final Object[] params = new Object[]{
                flowId, stepOrder, status
        };


        return jdbcTemplate.query(sql, params, new RequestMapper());
    }

    public List<Request> queryByIds(List<Integer> ids) {
        final String sql = String.format("select %s from t_request where id in (%s)",
                RequestMapper.RESULT_KEY_LIST,
                SqlUtil.selectInPlaceHolder(ids.size()));

        final Object[] params = new Object[ids.size()];
        ids.toArray(params);

        return jdbcTemplate.query(sql, params, new RequestMapper());
    }

    public int updateRequest(Map<String, Object> query, Map<String, Object> update) {

        Pair<String, List<Object>> u = SqlUtil.updateSetPlaceHolder(update);

        Pair<String, List<Object>> q = SqlUtil.updateWherePlaceHolder(query);

        List<Object> plist = new ArrayList<Object>(u.getValue().size() + q.getValue().size());
        Object[] param = new Object[plist.size()];
        plist.toArray(param);

        final String sql = String.format("update t_request set %s where %s", u.getKey(), q.getKey());

        return jdbcTemplate.update(sql, param);
    }


    private class RequestMapper implements RowMapper<Request> {

        public static final String RESULT_KEY_LIST = " id,user_id,flow_id,step_order,request_type,request_detail_json,approve,last_update_time ";

        @Override
        public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
            Request r = new Request();
            r.setId(rs.getInt("id"));
            r.setUserId(rs.getString("user_id"));
            r.setUserId(rs.getString("flow_id"));
            r.setStepOrder(rs.getInt("step_order"));
            r.setRequestType(rs.getString("request_type"));
            r.setRequestDetailJson(rs.getString("request_detail_json"));

            r.setApprove(rs.getInt("approve"));
            r.setLastUpdateTime(rs.getDate("last_update_time"));


            if (StringUtils.isNotBlank(r.getRequestDetailJson()))
                r.setRequestDetail(JSON.parseObject(r.getRequestDetailJson(), new TypeReference<Map<String, Object>>() {}));

            return r;
        }
    }
}
