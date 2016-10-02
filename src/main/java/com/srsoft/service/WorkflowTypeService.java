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
import com.srsoft.dao.WorkflowTypeMapper;
import com.srsoft.model.Role;
import com.srsoft.model.WorkflowType;
import com.srsoft.model.WorkflowTypeEx;
import com.srsoft.model.WorkflowTypeExample;
import com.srsoft.util.ServiceHelper;

public class WorkflowTypeService extends ServiceHelper {
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String str = request.getParameter("workflowType").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowTypeMapper mapper = sqlSession.getMapper(WorkflowTypeMapper.class);
		WorkflowType obj = mapper.selectByPrimaryKey(jsonObject.getInt("id"));
		
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
		WorkflowTypeMapper mapper = sqlSession.getMapper(WorkflowTypeMapper.class);
		 
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
		
		int companyId = Integer.parseInt(request.getParameter("companyId") == null ? "-1" : request.getParameter("companyId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		WorkflowTypeMapper mapper = sqlSession.getMapper(WorkflowTypeMapper.class);
		WorkflowTypeExample example = new WorkflowTypeExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<WorkflowTypeEx> list = mapper.queryWorkflowTypeList(start, limit, companyId);
		int i = 0;
		for (WorkflowTypeEx obj : list) {
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
		
		String str = request.getParameter("workflowType").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		WorkflowTypeMapper mapper = sqlSession.getMapper(WorkflowTypeMapper.class);
		WorkflowType obj = new WorkflowType();
		obj.setName(jsonObject.getString("name"));
		obj.setCompanyid(jsonObject.getInt("companyid"));
		
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