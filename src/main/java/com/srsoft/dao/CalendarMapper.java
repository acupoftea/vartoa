package com.srsoft.dao;

import com.srsoft.model.Calendar;
import com.srsoft.model.CalendarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CalendarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    int countByExample(CalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    int deleteByExample(CalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    int insert(Calendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    int insertSelective(Calendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    List<Calendar> selectByExample(CalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    Calendar selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    int updateByExampleSelective(@Param("record") Calendar record, @Param("example") CalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    int updateByExample(@Param("record") Calendar record, @Param("example") CalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    int updateByPrimaryKeySelective(Calendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table calendar
     *
     * @mbggenerated Mon Jul 18 05:41:51 CST 2016
     */
    int updateByPrimaryKey(Calendar record);
}