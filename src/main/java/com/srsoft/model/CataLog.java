package com.srsoft.model;

public class CataLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column weiz_catalog.id
     *
     * @mbggenerated Mon May 02 23:15:51 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column weiz_catalog.catalogName
     *
     * @mbggenerated Mon May 02 23:15:51 CST 2016
     */
    private String catalogname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column weiz_catalog.parentId
     *
     * @mbggenerated Mon May 02 23:15:51 CST 2016
     */
    private Integer parentid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column weiz_catalog.id
     *
     * @return the value of weiz_catalog.id
     *
     * @mbggenerated Mon May 02 23:15:51 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column weiz_catalog.id
     *
     * @param id the value for weiz_catalog.id
     *
     * @mbggenerated Mon May 02 23:15:51 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column weiz_catalog.catalogName
     *
     * @return the value of weiz_catalog.catalogName
     *
     * @mbggenerated Mon May 02 23:15:51 CST 2016
     */
    public String getCatalogname() {
        return catalogname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column weiz_catalog.catalogName
     *
     * @param catalogname the value for weiz_catalog.catalogName
     *
     * @mbggenerated Mon May 02 23:15:51 CST 2016
     */
    public void setCatalogname(String catalogname) {
        this.catalogname = catalogname == null ? null : catalogname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column weiz_catalog.parentId
     *
     * @return the value of weiz_catalog.parentId
     *
     * @mbggenerated Mon May 02 23:15:51 CST 2016
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column weiz_catalog.parentId
     *
     * @param parentid the value for weiz_catalog.parentId
     *
     * @mbggenerated Mon May 02 23:15:51 CST 2016
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}