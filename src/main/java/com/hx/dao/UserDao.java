package com.hx.dao;

import com.hx.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xh on 2015/2/27.
 */

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public User queryByIdPwd(String id, String pwd) {

        final String sql = String.format("select %s from t_user where id=? and password=?",
                UserMapper.RESULT_KEY_LIST);

        final Object[] params = new Object[] {
                id, pwd
        };

        try {
            return jdbcTemplate.queryForObject(sql, params, new UserMapper());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> queryByIds(List<String> ids) {

        if (ids == null || ids.size() == 0) {
            return null;
        }

        final String sql = String.format("select %s from t_user where id in (%s)",
                UserMapper.RESULT_KEY_LIST,
                SqlUtil.selectInPlaceHolder(ids.size())) ;


        final Object[] params = new Object[ids.size()];
        ids.toArray(params);


        return jdbcTemplate.query(sql, params, new UserMapper());
    }

    public Map<String, User> queryMapByIds(List<String> ids) {
        if (ids == null || ids.size() == 0) {
            return null;
        }
        List<User> list = queryByIds(ids);
        Map<String, User> r = new HashMap<String, User>();
        for (User u : list) {
            r.put(u.getId(), u);
        }
        return r;
    }

    private class UserMapper implements RowMapper<User> {

        public static final String RESULT_KEY_LIST = " id, name, password, department, level, last_login_time ";

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {

            User u = new User();
            u.setId(resultSet.getString("id"));
            u.setName(resultSet.getString("name"));
            u.setPassword(resultSet.getString("password"));
            u.setDepartment(resultSet.getString("department"));
            u.setLevel(resultSet.getInt("level"));
            u.setLastLoginTime(resultSet.getDate("last_login_time"));

            return u;
        }
    }
}
