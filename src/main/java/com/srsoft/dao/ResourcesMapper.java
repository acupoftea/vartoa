package com.srsoft.dao;

import com.srsoft.model.Resources;
import com.srsoft.model.ResourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourcesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    int countByExample(ResourcesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    int deleteByExample(ResourcesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    int insert(Resources record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    int insertSelective(Resources record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    List<Resources> selectByExample(ResourcesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    Resources selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    int updateByExampleSelective(@Param("record") Resources record, @Param("example") ResourcesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    int updateByExample(@Param("record") Resources record, @Param("example") ResourcesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    int updateByPrimaryKeySelective(Resources record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resources
     *
     * @mbggenerated Tue Aug 02 16:22:53 CST 2016
     */
    int updateByPrimaryKey(Resources record);
}