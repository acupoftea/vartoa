package com.srsoft.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.srsoft.dao.ResourcesMapper;
import com.srsoft.model.Resources;
import com.srsoft.model.ResourcesExample;
import com.srsoft.util.ServiceHelper;

public class ResourcesService extends ServiceHelper {
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String str = request.getParameter("resources").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		SqlSession sqlSession = getSessionFactory().openSession();
		ResourcesMapper mapper = sqlSession.getMapper(ResourcesMapper.class);
		Resources obj = mapper.selectByPrimaryKey(jsonObject.getInt("id"));
		
		if(obj != null) {
			obj.setName(jsonObject.getString("name"));
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
		ResourcesMapper mapper = sqlSession.getMapper(ResourcesMapper.class);
		 
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
		
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		ResourcesMapper mapper = sqlSession.getMapper(ResourcesMapper.class);
		ResourcesExample example = new ResourcesExample();
		example.or().andCompanyidEqualTo(companyId);
		int totalProperty  = mapper.countByExample(example);
		
		//List<Resources> list = mapper.selectAll4Paging(start, limit);
		List<Resources> list = mapper.selectByExample(example);
		int i = 0;
		for (Resources model : list) {
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
		
		String str = request.getParameter("resources").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		Resources obj = (Resources) JSONObject.toBean(jsonObject, Resources.class);
		ResourcesMapper mapper = sqlSession.getMapper(ResourcesMapper.class);
		
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