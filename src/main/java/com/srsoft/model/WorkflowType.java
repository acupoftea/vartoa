package com.srsoft.model;

public class WorkflowType {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowType.id
     *
     * @mbggenerated Sat Jul 09 08:23:52 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowType.name
     *
     * @mbggenerated Sat Jul 09 08:23:52 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflowType.companyId
     *
     * @mbggenerated Sat Jul 09 08:23:52 CST 2016
     */
    private Integer companyid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowType.id
     *
     * @return the value of workflowType.id
     *
     * @mbggenerated Sat Jul 09 08:23:52 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowType.id
     *
     * @param id the value for workflowType.id
     *
     * @mbggenerated Sat Jul 09 08:23:52 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowType.name
     *
     * @return the value of workflowType.name
     *
     * @mbggenerated Sat Jul 09 08:23:52 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowType.name
     *
     * @param name the value for workflowType.name
     *
     * @mbggenerated Sat Jul 09 08:23:52 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflowType.companyId
     *
     * @return the value of workflowType.companyId
     *
     * @mbggenerated Sat Jul 09 08:23:52 CST 2016
     */
    public Integer getCompanyid() {
        return companyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflowType.companyId
     *
     * @param companyid the value for workflowType.companyId
     *
     * @mbggenerated Sat Jul 09 08:23:52 CST 2016
     */
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }
}