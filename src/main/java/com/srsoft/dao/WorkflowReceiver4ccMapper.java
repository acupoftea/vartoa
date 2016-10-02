package com.srsoft.dao;

import com.srsoft.model.WorkflowReceiver4cc;
import com.srsoft.model.WorkflowReceiver4ccExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkflowReceiver4ccMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    int countByExample(WorkflowReceiver4ccExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    int deleteByExample(WorkflowReceiver4ccExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    int insert(WorkflowReceiver4cc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    int insertSelective(WorkflowReceiver4cc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    List<WorkflowReceiver4cc> selectByExample(WorkflowReceiver4ccExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    WorkflowReceiver4cc selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    int updateByExampleSelective(@Param("record") WorkflowReceiver4cc record, @Param("example") WorkflowReceiver4ccExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    int updateByExample(@Param("record") WorkflowReceiver4cc record, @Param("example") WorkflowReceiver4ccExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    int updateByPrimaryKeySelective(WorkflowReceiver4cc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    int updateByPrimaryKey(WorkflowReceiver4cc record);
}