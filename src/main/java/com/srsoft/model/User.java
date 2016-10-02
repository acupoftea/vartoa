package com.srsoft.model;

import java.util.Date;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.userName
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.roleId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private Integer roleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.remark
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.loginName
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private String loginname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.createTime
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.updateTime
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private Date updatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.companyId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private Integer companyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.departmentId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private Integer departmentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.mobile
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.email
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.workTimeId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private Integer worktimeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.employNu
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    private String employnu;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.userName
     *
     * @return the value of user.userName
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.userName
     *
     * @param username the value for user.userName
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.roleId
     *
     * @return the value of user.roleId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.roleId
     *
     * @param roleid the value for user.roleId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.remark
     *
     * @return the value of user.remark
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.remark
     *
     * @param remark the value for user.remark
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.loginName
     *
     * @return the value of user.loginName
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.loginName
     *
     * @param loginname the value for user.loginName
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.createTime
     *
     * @return the value of user.createTime
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.createTime
     *
     * @param createtime the value for user.createTime
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.updateTime
     *
     * @return the value of user.updateTime
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.updateTime
     *
     * @param updatetime the value for user.updateTime
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.companyId
     *
     * @return the value of user.companyId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public Integer getCompanyid() {
        return companyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.companyId
     *
     * @param companyid the value for user.companyId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.departmentId
     *
     * @return the value of user.departmentId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.departmentId
     *
     * @param departmentid the value for user.departmentId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.mobile
     *
     * @return the value of user.mobile
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.mobile
     *
     * @param mobile the value for user.mobile
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.email
     *
     * @return the value of user.email
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.email
     *
     * @param email the value for user.email
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.workTimeId
     *
     * @return the value of user.workTimeId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public Integer getWorktimeid() {
        return worktimeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.workTimeId
     *
     * @param worktimeid the value for user.workTimeId
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setWorktimeid(Integer worktimeid) {
        this.worktimeid = worktimeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.employNu
     *
     * @return the value of user.employNu
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public String getEmploynu() {
        return employnu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.employNu
     *
     * @param employnu the value for user.employNu
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    public void setEmploynu(String employnu) {
        this.employnu = employnu == null ? null : employnu.trim();
    }
}