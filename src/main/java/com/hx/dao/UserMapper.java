package com.hx.dao;

import com.hx.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xh on 2015/3/5.
 */
@Repository
public interface UserMapper {


    List<User> selectByIds(List<String> ids);

    List<User> selectByIdPwd(@Param("id")String id, @Param("pwd")String pwd);
}
