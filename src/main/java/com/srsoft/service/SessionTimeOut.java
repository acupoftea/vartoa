package com.srsoft.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class SessionTimeOut
 */
public class SessionTimeOut implements Filter {

	/**
	 * Default constructor.
	 */
	public SessionTimeOut() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		/*
		HttpServletRequest hsr = (HttpServletRequest) request;
		String beanId = request.getParameter("beanId") == null ? "" : request.getParameter("beanId");
		String bizType = request.getParameter("bizType") == null ? "" : request.getParameter("bizType");
		
		if (!beanId.equals("userService") && !bizType.equals("syslogin")) {
			Object obj = hsr.getSession().getAttribute("userMap");
			if (obj == null) {
				System.out.println("session ³¬Ê±");
				hsr.getRequestDispatcher("sessionTimeOut.jsp").forward(request, response);
			}
			else
				chain.doFilter(request, response);
		}
		else
			chain.doFilter(request, response);
		*/
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
