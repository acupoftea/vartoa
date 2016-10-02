package com.srsoft.model;

import java.util.Date;

public class Workflow {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.id
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.type
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.createTime
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.updateTime
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    private Date updatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.name
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.udfId
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    private Integer udfid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.companyId
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    private Integer companyid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.id
     *
     * @return the value of workflow.id
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.id
     *
     * @param id the value for workflow.id
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.type
     *
     * @return the value of workflow.type
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.type
     *
     * @param type the value for workflow.type
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.createTime
     *
     * @return the value of workflow.createTime
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.createTime
     *
     * @param createtime the value for workflow.createTime
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.updateTime
     *
     * @return the value of workflow.updateTime
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.updateTime
     *
     * @param updatetime the value for workflow.updateTime
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.name
     *
     * @return the value of workflow.name
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.name
     *
     * @param name the value for workflow.name
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.udfId
     *
     * @return the value of workflow.udfId
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public Integer getUdfid() {
        return udfid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.udfId
     *
     * @param udfid the value for workflow.udfId
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public void setUdfid(Integer udfid) {
        this.udfid = udfid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.companyId
     *
     * @return the value of workflow.companyId
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public Integer getCompanyid() {
        return companyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.companyId
     *
     * @param companyid the value for workflow.companyId
     *
     * @mbggenerated Sat Jul 09 13:31:32 CST 2016
     */
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }
}