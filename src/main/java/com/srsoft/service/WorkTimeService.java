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
import com.srsoft.dao.WorkTimeMapper;
import com.srsoft.model.WorkTime;
import com.srsoft.model.WorkTimeEx;
import com.srsoft.model.WorkTimeExample;
import com.srsoft.model.WorkflowEx;
import com.srsoft.util.ServiceHelper;

public class WorkTimeService extends ServiceHelper {
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkTimeMapper mapper = sqlSession.getMapper(WorkTimeMapper.class);
		WorkTime obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
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
		WorkTimeMapper mapper = sqlSession.getMapper(WorkTimeMapper.class);
		 
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
		
		WorkTimeMapper mapper = sqlSession.getMapper(WorkTimeMapper.class);
		WorkTimeExample example = new WorkTimeExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<WorkTimeEx> list = mapper.selectAll4Paging(start, limit, companyId);
		//List<WorkTime> list = mapper.selectByExample(example);
		int i = 0;
		for (WorkTimeEx obj : list) {
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
		
		String str = request.getParameter("workTime").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonStr: " + str);
		
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		WorkTimeMapper mapper = sqlSession.getMapper(WorkTimeMapper.class);
		WorkTime obj = new WorkTime();
		obj.setName(jsonObject.getString("name"));
		obj.setStartdate(jsonObject.getString("startdate"));
		obj.setStarttime(jsonObject.getString("starttime"));
		obj.setRange(jsonObject.getString("range"));
		obj.setEndtime(jsonObject.getString("endtime"));
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