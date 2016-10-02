package com.srsoft.dao;

import com.srsoft.model.Leave;
import com.srsoft.model.LeaveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeaveMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    int countByExample(LeaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    int deleteByExample(LeaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    int insert(Leave record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    int insertSelective(Leave record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    List<Leave> selectByExample(LeaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    Leave selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    int updateByExampleSelective(@Param("record") Leave record, @Param("example") LeaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    int updateByExample(@Param("record") Leave record, @Param("example") LeaveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    int updateByPrimaryKeySelective(Leave record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave
     *
     * @mbggenerated Wed Jun 29 08:34:15 CST 2016
     */
    int updateByPrimaryKey(Leave record);
}