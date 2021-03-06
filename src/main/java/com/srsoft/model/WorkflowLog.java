package com.srsoft.model;

import java.util.Date;

public class WorkflowLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowLog.id
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowLog.workflowInstance
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    private Integer workflowinstance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowLog.operator
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    private Integer operator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowLog.action
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    private String action;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowLog.createTime
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowLog.workflowNodeId
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    private Integer workflownodeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowLog.remark
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowLog.isForward
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    private Integer isforward;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowLog.id
     *
     * @return the value of workflowLog.id
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowLog.id
     *
     * @param id the value for workflowLog.id
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowLog.workflowInstance
     *
     * @return the value of workflowLog.workflowInstance
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public Integer getWorkflowinstance() {
        return workflowinstance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowLog.workflowInstance
     *
     * @param workflowinstance the value for workflowLog.workflowInstance
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public void setWorkflowinstance(Integer workflowinstance) {
        this.workflowinstance = workflowinstance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowLog.operator
     *
     * @return the value of workflowLog.operator
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public Integer getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowLog.operator
     *
     * @param operator the value for workflowLog.operator
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowLog.action
     *
     * @return the value of workflowLog.action
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowLog.action
     *
     * @param action the value for workflowLog.action
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowLog.createTime
     *
     * @return the value of workflowLog.createTime
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowLog.createTime
     *
     * @param createtime the value for workflowLog.createTime
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowLog.workflowNodeId
     *
     * @return the value of workflowLog.workflowNodeId
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public Integer getWorkflownodeid() {
        return workflownodeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowLog.workflowNodeId
     *
     * @param workflownodeid the value for workflowLog.workflowNodeId
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public void setWorkflownodeid(Integer workflownodeid) {
        this.workflownodeid = workflownodeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowLog.remark
     *
     * @return the value of workflowLog.remark
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowLog.remark
     *
     * @param remark the value for workflowLog.remark
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowLog.isForward
     *
     * @return the value of workflowLog.isForward
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public Integer getIsforward() {
        return isforward;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowLog.isForward
     *
     * @param isforward the value for workflowLog.isForward
     *
     * @mbggenerated Sun Jul 03 12:46:11 CST 2016
     */
    public void setIsforward(Integer isforward) {
        this.isforward = isforward;
    }
}