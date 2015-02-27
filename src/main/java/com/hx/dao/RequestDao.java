package com.hx.dao;

import com.hx.domain.Request;
import com.hx.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

        final String sql = String.format("select %s from t_request where flow_id=? and step_order=? and status=?",
                RequestMapper.RESULT_KEY_LIST) ;

        final Object[] params = new Object[] {
                flowId, stepOrder, status
        };


        return jdbcTemplate.query(sql, params, new RequestMapper());
    }

    public List<Request> queryByIds(List<Integer> ids) {
        final String sql = String.format("select %s from t_request where id in (%s)",
                RequestMapper.RESULT_KEY_LIST,
                SqlUtil.placeHolder(ids.size())) ;

        final Object[] params = new Object[ids.size()];
        ids.toArray(params);

        return jdbcTemplate.query(sql, params, new RequestMapper());
    }

    private class RequestMapper implements RowMapper<Request> {

        public static final String RESULT_KEY_LIST = " id, name, password, department, last_login_time ";

        @Override
        public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
            Request r = new Request();


            return r;
        }
    }
}
