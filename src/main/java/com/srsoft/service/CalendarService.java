package com.srsoft.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.srsoft.dao.CalendarMapper;
import com.srsoft.dao.CalendarTypeMapper;
import com.srsoft.model.Calendar;
import com.srsoft.model.CalendarExample;
import com.srsoft.model.CalendarType;
import com.srsoft.model.CalendarTypeExample;
import com.srsoft.util.ServiceHelper;

public class CalendarService extends ServiceHelper {
	public void queryCalendarById(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		SqlSession sqlSession = getSessionFactory().openSession();
		
		String eventId = request.getParameter("eventId");
		
		CalendarMapper mapper = sqlSession.getMapper(CalendarMapper.class);
		Calendar calendar = mapper.selectByPrimaryKey(Integer.parseInt(eventId));
		
		sqlSession.close();
		
		map.put("success", true);
		map.put("data", calendar);
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	public void query4Calendar(HttpServletRequest request, HttpServletResponse response) {
		SqlSession sqlSession = getSessionFactory().openSession();
		
		String userId = request.getParameter("userId");
		//String calendarType = request.getParameter("calendarType");
		
		CalendarMapper mapper = sqlSession.getMapper(CalendarMapper.class);
		CalendarExample example = new CalendarExample();
		example.or().andOwnerEqualTo(Integer.parseInt(userId));//.andCalendartypeEqualTo(Integer.parseInt(calendarType));
		
		List<Calendar> list = mapper.selectByExample(example);
		
		sqlSession.close();
		
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		for (Calendar calendar : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", calendar.getId());
			map.put("title", calendar.getEventname());
			map.put("start", calendar.getStartendtime().split("-")[0]);
			map.put("end", calendar.getStartendtime().split("-")[1]);
			
			if(calendar.getCalendartype() == 1)
				map.put("color", "#ff0000");
			if(calendar.getCalendartype() == 2)
				map.put("color", "#00ff00");
			if(calendar.getCalendartype() == 3)
				map.put("color", "#0000ff");
			
			list2.add(map);
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list2);
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	public void query4calendarTypeList(HttpServletRequest request, HttpServletResponse response) {
		SqlSession sqlSession = getSessionFactory().openSession();
		
		CalendarTypeMapper mapper = sqlSession.getMapper(CalendarTypeMapper.class);
		CalendarTypeExample example = new CalendarTypeExample();
		int totalProperty  = mapper.countByExample(example);
		
		//List<Calendar> list = mapper.selectAll4Paging(start, limit);
		List<CalendarType> list = mapper.selectByExample(example);
		
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
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String str = request.getParameter("calendar").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonStr: " + str);
		
		JSONObject jsonObject = JSONObject.fromObject(str);
		Calendar obj = (Calendar) JSONObject.toBean(jsonObject, Calendar.class);
		
		SqlSession sqlSession = getSessionFactory().openSession();
		CalendarMapper mapper = sqlSession.getMapper(CalendarMapper.class);
		
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
		CalendarMapper mapper = sqlSession.getMapper(CalendarMapper.class);
		 
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
		
		CalendarMapper mapper = sqlSession.getMapper(CalendarMapper.class);
		CalendarExample example = new CalendarExample();
		int totalProperty  = mapper.countByExample(example);
		
		//List<Calendar> list = mapper.selectAll4Paging(start, limit);
		List<Calendar> list = mapper.selectByExample(example);
		
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
		
		String str = request.getParameter("calendar").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonStr: " + str);
		
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		CalendarMapper mapper = sqlSession.getMapper(CalendarMapper.class);
		Calendar obj = new Calendar();
		obj.setCalendartype(jsonObject.getInt("type"));
		obj.setEventname(jsonObject.getString("eventname"));
		if(jsonObject.get("eventdetail") != null)
			obj.setEnvetdetail(jsonObject.getString("eventdetail"));
		
		obj.setOwner(jsonObject.getInt("userid"));
		obj.setCreatetime(new Date());
		obj.setStartendtime(jsonObject.getString("eventstartendtime"));
		
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