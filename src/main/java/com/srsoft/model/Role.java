package com.srsoft.model;

public class Role {
	int index;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.id
     *
     * @mbggenerated Fri Jul 29 08:58:02 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.roleName
     *
     * @mbggenerated Fri Jul 29 08:58:02 CST 2016
     */
    private String rolename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.companyId
     *
     * @mbggenerated Fri Jul 29 08:58:02 CST 2016
     */
    private Integer companyid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.id
     *
     * @return the value of role.id
     *
     * @mbggenerated Fri Jul 29 08:58:02 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.id
     *
     * @param id the value for role.id
     *
     * @mbggenerated Fri Jul 29 08:58:02 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.roleName
     *
     * @return the value of role.roleName
     *
     * @mbggenerated Fri Jul 29 08:58:02 CST 2016
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.roleName
     *
     * @param rolename the value for role.roleName
     *
     * @mbggenerated Fri Jul 29 08:58:02 CST 2016
     */
    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.companyId
     *
     * @return the value of role.companyId
     *
     * @mbggenerated Fri Jul 29 08:58:02 CST 2016
     */
    public Integer getCompanyid() {
        return companyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.companyId
     *
     * @param companyid the value for role.companyId
     *
     * @mbggenerated Fri Jul 29 08:58:02 CST 2016
     */
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
    
}