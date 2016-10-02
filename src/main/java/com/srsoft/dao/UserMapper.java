package com.srsoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.srsoft.model.PowerEx;
import com.srsoft.model.User;
import com.srsoft.model.UserEx;
import com.srsoft.model.UserExample;

public interface UserMapper {
	List<PowerEx> queryUserPowerExByRole(@Param("roleId") Integer arg0);
	List<UserEx> selectAll4Paging(@Param("start") Integer start, @Param("limit") Integer limit);
	List<UserEx> selectByCompanyId4Paging(@Param("start") Integer start, @Param("limit") Integer limit, @Param("companyId") Integer companyId,
			@Param("orderColumn") String arg3, @Param("orderDir") String arg4);
	
	UserEx selectByUnPw(@Param("un") String arg0, @Param("pw") String arg1);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    int countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated Mon Jul 25 07:57:55 CST 2016
     */
    int updateByPrimaryKey(User record);
}