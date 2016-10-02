package com.srsoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.srsoft.model.Deparment;
import com.srsoft.model.DeparmentEx;
import com.srsoft.model.DeparmentExample;

public interface DeparmentMapper {
	List<DeparmentEx> selectAll4Paging(@Param("start") Integer start, @Param("limit") Integer limit, @Param("companyId") Integer companyId);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    int countByExample(DeparmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    int deleteByExample(DeparmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    int insert(Deparment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    int insertSelective(Deparment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    List<Deparment> selectByExample(DeparmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    Deparment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    int updateByExampleSelective(@Param("record") Deparment record, @Param("example") DeparmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    int updateByExample(@Param("record") Deparment record, @Param("example") DeparmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    int updateByPrimaryKeySelective(Deparment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deparment
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    int updateByPrimaryKey(Deparment record);
}