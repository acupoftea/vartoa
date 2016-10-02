package com.srsoft.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.srsoft.dao.PowerMapper;
import com.srsoft.model.Model;
import com.srsoft.model.Power;
import com.srsoft.model.PowerEx;
import com.srsoft.model.PowerExample;
import com.srsoft.util.ServiceHelper;

public class PowerService extends ServiceHelper {
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		PowerMapper mapper = sqlSession.getMapper(PowerMapper.class);
		Power obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
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
		PowerMapper mapper = sqlSession.getMapper(PowerMapper.class);
		 
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
		
		PowerMapper mapper = sqlSession.getMapper(PowerMapper.class);
		PowerExample example = new PowerExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<PowerEx> list = mapper.selectAll4Paging(start, limit);
		//List<Power> list = mapper.selectByExample(example);
		int i = 0;
		for (PowerEx power : list) {
			power.setIndex(++i);
		}
		
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
		
		String modelId = request.getParameter("modelId");
		String parentId = request.getParameter("parentId");
		String sequence = request.getParameter("sequence");
		
		PowerMapper mapper = sqlSession.getMapper(PowerMapper.class);
		Power obj = new Power();
		obj.setModel(Integer.parseInt(modelId));
		obj.setParentid(parentId);
		obj.setSequence(Integer.parseInt(sequence));
		
		
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
