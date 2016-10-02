package com.srsoft.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.srsoft.dao.LeaveMapper;
import com.srsoft.model.Leave;
import com.srsoft.model.LeaveExample;
import com.srsoft.util.ServiceHelper;

public class LeaveService extends ServiceHelper {
	public void queryLeaveById(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id") == null ? "-1" : request.getParameter("id"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
		Leave leave = mapper.selectByPrimaryKey(id);
		sqlSession.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(leave != null)
			map.put("success", true);
		else
			map.put("success", false);
		map.put("data", leave);
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{success: false}";
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
		Leave obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
		if(obj != null) {
			
			int rtn = mapper.updateByPrimaryKey(obj);
			if(rtn > 0)
				jSon = "{success: true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtnData = "{success:false}";
		String id = request.getParameter("id").toString();
		
		SqlSession sqlSession = this.getSessionFactory().openSession();;
		LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
		 
		int rtn = mapper.deleteByPrimaryKey(Integer.parseInt(id));
		if(rtn >= 0) {
			rtnData = "{success:true}";
		}
		
		sqlSession.close();
		
		returnJson(response, rtnData);
	}
	
	public void query(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
		LeaveExample example = new LeaveExample();
		int totalProperty  = mapper.countByExample(example);
		
		//List<Leave> list = mapper.selectAll4Paging(start, limit);
		List<Leave> list = mapper.selectByExample(example);
		
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
		
		String str = request.getParameter("data").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
		Leave obj = new Leave();
		obj.setCreatetime(new Date());
		obj.setReason(jsonObject.getString("reason"));
		obj.setUserid(jsonObject.getInt("userid"));
		
		String dateRange = jsonObject.getString("daterange");
		obj.setStarttime(new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(dateRange.split("-")[0]));
		obj.setEndtime(new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(dateRange.split("-")[1]));
		
		int rtn = mapper.insert(obj);
		if(rtn > 0) {
			WorkflowInstanceService wfis = new WorkflowInstanceService();
			rtn = wfis.startWorkflow(1, obj.getUserid(), obj.getId(), obj.getReason());
			if(rtn > 0)
				jSon = "{\"success\": true}";
		}
		
		sqlSession.close();
		
		try {
			this.returnJson(response, jSon);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
}