package com.srsoft.model;

import java.util.Date;

public class Log {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ac_log.id
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ac_log.userId
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ac_log.action
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    private String action;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ac_log.data1
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    private String data1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ac_log.data2
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    private String data2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ac_log.data3
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    private String data3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ac_log.createTime
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ac_log.id
     *
     * @return the value of ac_log.id
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ac_log.id
     *
     * @param id the value for ac_log.id
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ac_log.userId
     *
     * @return the value of ac_log.userId
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ac_log.userId
     *
     * @param userid the value for ac_log.userId
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ac_log.action
     *
     * @return the value of ac_log.action
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ac_log.action
     *
     * @param action the value for ac_log.action
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ac_log.data1
     *
     * @return the value of ac_log.data1
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public String getData1() {
        return data1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ac_log.data1
     *
     * @param data1 the value for ac_log.data1
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public void setData1(String data1) {
        this.data1 = data1 == null ? null : data1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ac_log.data2
     *
     * @return the value of ac_log.data2
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public String getData2() {
        return data2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ac_log.data2
     *
     * @param data2 the value for ac_log.data2
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public void setData2(String data2) {
        this.data2 = data2 == null ? null : data2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ac_log.data3
     *
     * @return the value of ac_log.data3
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public String getData3() {
        return data3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ac_log.data3
     *
     * @param data3 the value for ac_log.data3
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public void setData3(String data3) {
        this.data3 = data3 == null ? null : data3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ac_log.createTime
     *
     * @return the value of ac_log.createTime
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ac_log.createTime
     *
     * @param createtime the value for ac_log.createTime
     *
     * @mbggenerated Wed Mar 16 01:11:26 CST 2016
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}