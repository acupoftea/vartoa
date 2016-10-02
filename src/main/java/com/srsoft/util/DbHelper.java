package com.srsoft.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class DbHelper  {
	String resource = "mybatis_configuration.xml";
	SqlSessionFactory sqlSessionFactory = null;  
    
    public DbHelper() {
	    try {  
	    	sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource)); 
	        
	    } catch (IOException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    }
    }
    
    public SqlSession getSqlSession() {
    	// false 关闭自动提交，不过 mysql 的表要改成 InnoDB这个参数才有效
    	//return sqlSessionFactory.openSession(false);
    	return sqlSessionFactory.openSession();
    }
	
	public int deleteData(String sql) {
		return 0;
	}
	
	public int updateData(String sql) {
		return 0;
	}
	
	public List<Map<String, Object>> queryData(String sql) {
		return null;
	}
	
	public int insertData(String sql) {
		return 0;
	}
	
	public void beginTransaction() {
		
	}
	
	public void rollbackTransaction() {
			
	}
	
	public void endTransaction() {
		
	}
}
