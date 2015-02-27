package com.hx.dao;

import com.hx.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        final String sql = "select id, name, password, department, last_login_time from t_user where id=? and password=?";

        final Object[] params = new Object[] {
                id, pwd
        };

        try {
            return jdbcTemplate.queryForObject(sql, params, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {

                    User u = new User();
                    u.setId(resultSet.getString("id"));
                    u.setName(resultSet.getString("name"));
                    u.setPassword(resultSet.getString("password"));
                    u.setDepartment(resultSet.getString("department"));
                    u.setLastLoginTime(resultSet.getDate("last_login_time"));


                    return u;
                }
            });
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        }
    }
}
