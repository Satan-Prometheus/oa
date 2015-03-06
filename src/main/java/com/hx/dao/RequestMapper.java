package com.hx.dao;

import java.util.List;

import com.hx.domain.Request;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    int countByExample(RequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    int deleteByExample(RequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    int insert(Request record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    int insertSelective(Request record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    List<Request> selectByExample(RequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    Request selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    int updateByExampleSelective(@Param("record") Request record, @Param("example") RequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    int updateByExample(@Param("record") Request record, @Param("example") RequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    int updateByPrimaryKeySelective(Request record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_request
     *
     * @mbggenerated Fri Mar 06 21:57:55 CST 2015
     */
    int updateByPrimaryKey(Request record);
}