package com.srsoft.model;

public class Power {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power.id
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power.model
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    private Integer model;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power.parentId
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    private String parentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column power.sequence
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    private Integer sequence;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power.id
     *
     * @return the value of power.id
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power.id
     *
     * @param id the value for power.id
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power.model
     *
     * @return the value of power.model
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    public Integer getModel() {
        return model;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power.model
     *
     * @param model the value for power.model
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    public void setModel(Integer model) {
        this.model = model;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power.parentId
     *
     * @return the value of power.parentId
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power.parentId
     *
     * @param parentid the value for power.parentId
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column power.sequence
     *
     * @return the value of power.sequence
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column power.sequence
     *
     * @param sequence the value for power.sequence
     *
     * @mbggenerated Tue Jun 28 03:40:16 CST 2016
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}