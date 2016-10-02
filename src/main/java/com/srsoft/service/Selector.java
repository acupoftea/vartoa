package com.srsoft.service;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class Selector {
	Logger logger = Logger.getLogger(this.getClass()); 
	
	Set<Object> workers = null;

	public Set<Object> getWorkers() {
		return workers;
	}

	public void setWorkers(Set<Object> workers) {
		this.workers = workers;
	}

	public void select(ApplicationContext context, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("request parameter list");
		Enumeration<String> pNames = request.getParameterNames();
		while(pNames.hasMoreElements()){
		    String name = (String)pNames.nextElement();
		    String value = request.getParameter(name);
		    //logger.info(name + " = " + value);
		    System.out.println(name + " = " + value);
		}
		
		String beanId = request.getParameter("beanId") == null ? "" :  request.getParameter("beanId");
		
		boolean isFind = false;
		for (Object myObject : workers) {
			String _beanId = context.getBeanNamesForType(myObject.getClass())[0];
			
			if(_beanId.equals(beanId)) {
				isFind = true;
				
				Class<?> _class = myObject.getClass();
				Method[] method = _class.getMethods();
				for (Method myMethod : method) {
					if(myMethod.getName().equals("entry")) {
						myMethod.invoke(myObject, request, response);
						
						return;
					}
				}
				
				break;
			}
			
		}
		
		if(!isFind)
			logger.error("not find benaId: " + beanId);
	}
}
