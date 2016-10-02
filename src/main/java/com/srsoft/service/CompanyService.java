package com.srsoft.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.srsoft.dao.CompanyMapper;
import com.srsoft.model.Company;
import com.srsoft.model.CompanyExample;
import com.srsoft.util.ServiceHelper;

public class CompanyService extends ServiceHelper {
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
		Company obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
		if(obj != null) {
			
			int rtn = mapper.updateByPrimaryKey(obj);
			if(rtn > 0)
				jSon = "{\"success\": true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtnData = "{\"success\":false}";
		String id = request.getParameter("id").toString();
		
		SqlSession sqlSession = this.getSessionFactory().openSession();;
		CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
		 
		int rtn = mapper.deleteByPrimaryKey(Integer.parseInt(id));
		if(rtn >= 0) {
			rtnData = "{\"success\":true}";
		}
		
		sqlSession.close();
		
		returnJson(response, rtnData);
	}
	
	public void query(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
		CompanyExample example = new CompanyExample();
		int totalProperty  = mapper.countByExample(example);
		
		//List<Company> list = mapper.selectAll4Paging(start, limit);
		List<Company> list = mapper.selectByExample(example);
		
		sqlSession.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("recordsTotal", totalProperty);
		map.put("recordsFiltered", totalProperty);
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	public void create(HttpServletRequest request, HttpServletResponse response) {
		String jSon = "{\"success\": false}";
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		String name = request.getParameter("name");
		
		
		CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
		Company obj = new Company();
		
		int rtn = mapper.insert(obj);
		if(rtn > 0)
			jSon = "{\"success\": true}";
		
		sqlSession.close();
		
		try {
			this.returnJson(response, jSon);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
}