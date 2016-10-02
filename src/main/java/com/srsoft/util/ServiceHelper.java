package com.srsoft.util;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class ServiceHelper {
	public CacheManager cacheManager = CacheManager.create();
	public Logger logger = Logger.getLogger(this.getClass()); 
	public DbHelper db = new DbHelper();
	
	public SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;  
        try {  
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis_configuration.xml")); 
            
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
        
        return sessionFactory;  
    }
	
	public void entry(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		String bizType = request.getParameter("bizType");
		logger.info("bizType: " + bizType);
		
		try {
			Class<?> _class = this.getClass();
			Method[] method = _class.getMethods();
			
			for (Method myMethod : method) {
				if(myMethod.getName().equals(bizType)) {
					myMethod.invoke(this, request, response);
					break;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			logger.error(e.getCause());
			
			try {
				String errorMsg = "";
				if(e.getMessage() != null && e.getMessage().indexOf("Duplicate") > 0)
					errorMsg = "不能重复录入";
				
				if(e.getCause() != null && e.getCause().toString().indexOf("Duplicate") > 0)
					errorMsg = "不能重复录入";
				
				this.returnJson(response, "{\"success\": false, \"msg\":\"" + errorMsg + "\"}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void returnJson(HttpServletResponse response, String jSon) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jSon);
	}
}
