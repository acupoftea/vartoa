package com.srsoft.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.srsoft.dao.ModelMapper;
import com.srsoft.model.Model;
import com.srsoft.model.ModelExample;
import com.srsoft.util.ServiceHelper;

public class ModelService extends ServiceHelper {
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		name = URLDecoder.decode(URLDecoder.decode(name, "utf-8"), "utf-8");
		String url = request.getParameter("url");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		ModelMapper mapper = sqlSession.getMapper(ModelMapper.class);
		Model obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
		if(obj != null) {
			obj.setName(name);
			obj.setUrl(url);
			
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
		ModelMapper mapper = sqlSession.getMapper(ModelMapper.class);
		 
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
		
		ModelMapper mapper = sqlSession.getMapper(ModelMapper.class);
		ModelExample example = new ModelExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<Model> list = mapper.selectAll4Paging(start, limit);
		//List<Model> list = mapper.selectByExample(example);
		int i = 0;
		for (Model model : list) {
			model.setIndex(++i);
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
		
		String name = request.getParameter("name");
		name = URLDecoder.decode(URLDecoder.decode(name, "utf-8"), "utf-8");
		String url = request.getParameter("url");
		
		ModelMapper mapper = sqlSession.getMapper(ModelMapper.class);
		Model obj = new Model();
		obj.setName(name);
		obj.setUrl(url);
		
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