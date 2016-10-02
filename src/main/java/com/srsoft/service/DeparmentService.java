package com.srsoft.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.srsoft.dao.DeparmentMapper;
import com.srsoft.dao.UserMapper;
import com.srsoft.model.Deparment;
import com.srsoft.model.DeparmentEx;
import com.srsoft.model.DeparmentExample;
import com.srsoft.model.User;
import com.srsoft.model.WorkflowTypeEx;
import com.srsoft.util.ServiceHelper;

public class DeparmentService extends ServiceHelper {
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String str = request.getParameter("deparment").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		Deparment obj = (Deparment) JSONObject.toBean(jsonObject, Deparment.class);
		
		SqlSession sqlSession = getSessionFactory().openSession();
		DeparmentMapper mapper = sqlSession.getMapper(DeparmentMapper.class);
		//Deparment obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
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
		DeparmentMapper mapper = sqlSession.getMapper(DeparmentMapper.class);
		 
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
		
		int companyId = Integer.parseInt(request.getParameter("companyId") == null ? "0" : request.getParameter("companyId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		DeparmentMapper mapper = sqlSession.getMapper(DeparmentMapper.class);
		DeparmentExample example = new DeparmentExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<DeparmentEx> list = mapper.selectAll4Paging(start, limit, companyId);
		//List<Deparment> list = mapper.selectByExample(example);
		int i = 0;
		for (DeparmentEx obj : list) {
			obj.setIndex(++i);
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
	
	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		String str = request.getParameter("deparment").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		Deparment obj = (Deparment) JSONObject.toBean(jsonObject, Deparment.class);
		DeparmentMapper mapper = sqlSession.getMapper(DeparmentMapper.class);
		
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