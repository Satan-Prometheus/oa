package com.hx.dao;

import com.hx.domain.Request;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xh on 2015/3/4.
 */

@Repository
public interface RequestMapper {

    int insertRequest(Request request);

    List<Request> selectRequests(Map<Object, Object> map);

    List<Request> selectByIds(List<Integer> ids);

    int updateRequest(@Param("query") Map<Object, Object> query, @Param("value") Request value);
}
