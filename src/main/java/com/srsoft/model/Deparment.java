package com.srsoft.model;

public class Deparment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deparment.id
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deparment.name
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deparment.companyId
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    private Integer companyid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deparment.id
     *
     * @return the value of deparment.id
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deparment.id
     *
     * @param id the value for deparment.id
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deparment.name
     *
     * @return the value of deparment.name
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deparment.name
     *
     * @param name the value for deparment.name
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deparment.companyId
     *
     * @return the value of deparment.companyId
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    public Integer getCompanyid() {
        return companyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deparment.companyId
     *
     * @param companyid the value for deparment.companyId
     *
     * @mbggenerated Mon Jul 25 08:16:32 CST 2016
     */
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }
}