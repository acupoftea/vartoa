package com.srsoft.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Servlet implementation class Commander
 */
public class Commander extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(this.getClass()); 
	ApplicationContext context = null;
	Selector selector = null;
	
	void pretreatment(HttpServletRequest request, HttpServletResponse response) {
		try {
			selector.select(context, request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Commander() {
        super();
        // TODO Auto-generated constructor stub
        context = new ClassPathXmlApplicationContext("spring_*.xml");
        selector = (Selector)context.getBean("selector");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("\ndoGet");
		pretreatment(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("\ndoPost");
		pretreatment(request, response);
	}

}
