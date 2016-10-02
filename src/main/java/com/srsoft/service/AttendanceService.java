package com.srsoft.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.srsoft.dao.AttendanceMapper;
import com.srsoft.dao.CalendarMapper;
import com.srsoft.dao.CompanyMapper;
import com.srsoft.dao.UserMapper;
import com.srsoft.dao.WorkTimeMapper;
import com.srsoft.model.Attendance;
import com.srsoft.model.AttendanceEx;
import com.srsoft.model.AttendanceExample;
import com.srsoft.model.CalendarExample;
import com.srsoft.model.Company;
import com.srsoft.model.StatisticsAttendance;
import com.srsoft.model.User;
import com.srsoft.model.WorkTime;
import com.srsoft.util.ServiceHelper;

public class AttendanceService extends ServiceHelper {
	public void query4Calendar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		
		/*
		String selectedDate = request.getParameter("selectedDate") == null ? "" : request.getParameter("selectedDate");
		//获取当前月第一天：
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();    
		c.add(Calendar.MONTH, 0);
		//设置为1号,当前日期既为本月第一天 
		c.set(Calendar.DAY_OF_MONTH,1);
		String first = format.format(c.getTime());
		first += " 00:00:00";
		 
		//获取当前月最后一天
		Calendar ca = Calendar.getInstance();    
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		String last = format.format(ca.getTime());
		last += " 23:59:59";
		
		String[] abData = selectedDate.split("-");
		if(abData.length > 1) {
			first = abData[0].trim();
			last = abData[1].trim();
		}
		*/
		
		SqlSession sqlSession = getSessionFactory().openSession();
		AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
		AttendanceExample example = new AttendanceExample();
		example.or().andUseridEqualTo(Integer.parseInt(userId));
		
		List<Attendance> list = mapper.selectByExample(example);
		sqlSession.close();
		
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		for (Attendance attendance : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", attendance.getId());
			
			String startTime = "";
			if(attendance.getStarttime() != null)
				startTime = new SimpleDateFormat("HH:mm:ss").format(attendance.getStarttime());
			String endTime = "";
			if(attendance.getEndtime() != null)
				endTime = new SimpleDateFormat("HH:mm:ss").format(attendance.getEndtime());
			
			
			map.put("start", attendance.getCreatetime());
			map.put("end", attendance.getUpdatetime());
			if(attendance.getChid() != null && attendance.getChid().equals(1)) {
				map.put("color", "#ff0000");
				map.put("title", startTime + " ~ " + endTime + " 迟到");
			}
			else {
				map.put("color", "#00ff00");
				map.put("title", startTime + " ~ " + endTime + " 正常");
			}
			
			list2.add(map);
			
			if(attendance.getZaot() != null && attendance.getZaot().equals(1)) {
				map = new HashMap<String, Object>();
				
				map.put("id", attendance.getId());
				map.put("title", startTime + " ~ " + endTime + " 早退");
				map.put("start", attendance.getCreatetime());
				map.put("end", attendance.getUpdatetime());
				map.put("color", "#000000");
				list2.add(map);
			}
			
			if(attendance.getIperror() != null && attendance.getIperror().equals(1)) {
				map = new HashMap<String, Object>();
				
				map.put("id", attendance.getId());
				map.put("title", startTime + " ~ " + endTime + " IP异常");
				map.put("start", attendance.getCreatetime());
				map.put("end", attendance.getUpdatetime());
				map.put("color", "#999999");
				list2.add(map);
			}
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
	
	public void updateCompanyId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
		Company obj = mapper.selectByPrimaryKey(companyId);
		
		if(obj != null) {
			obj.setIp(request.getRemoteAddr());
			
			int rtn = mapper.updateByPrimaryKey(obj);
			if(rtn > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("success", true);
				map.put("data", obj.getIp());
				
				jSon = new Gson().toJson(map);
			}
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void queryAttendanceCalendar(HttpServletRequest request, HttpServletResponse response) {
		SqlSession sqlSession = getSessionFactory().openSession();
		
		String userId = request.getParameter("userId");
		String calendarType = request.getParameter("calendarType");
		
		CalendarMapper mapper = sqlSession.getMapper(CalendarMapper.class);
		CalendarExample example = new CalendarExample();
		example.or().andOwnerEqualTo(Integer.parseInt(userId)).andCalendartypeEqualTo(Integer.parseInt(calendarType));
		
		List<com.srsoft.model.Calendar> list = mapper.selectByExample(example);
		
		sqlSession.close();
		
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		for (com.srsoft.model.Calendar calendar : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", calendar.getId());
			map.put("title", calendar.getEventname());
			map.put("start", calendar.getStartendtime().split("-")[0]);
			map.put("end", calendar.getStartendtime().split("-")[1]);
			
			map.put("color", "#00ff00");
			
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
	
	public void statisticsAttendanceDetail4Month(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String userId = request.getParameter("userId");
		String selectedDate = request.getParameter("selectedDate");
		
		//获取当前月第一天：
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();    
		c.add(Calendar.MONTH, 0);
		//设置为1号,当前日期既为本月第一天 
		c.set(Calendar.DAY_OF_MONTH,1);
		String first = format.format(c.getTime());
		first += " 00:00:00";
		 
		//获取当前月最后一天
		Calendar ca = Calendar.getInstance();    
		ca.set(Calendar.DAY_OF_MONTH, 
			ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		String last = format.format(ca.getTime());
		last += " 23:59:59";
		
		String[] abData = selectedDate.split("-");
		if(abData.length > 1) {
			first = abData[0].trim();
			last = abData[1].trim();
		}
		
		SqlSession sqlSession = getSessionFactory().openSession();
		AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
				
		StatisticsAttendance sa = mapper.statisticsAttendance4Month(Integer.parseInt(userId), first, last);
		sqlSession.close();
		
		//if(sa != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			map.put("data", sa);
			
			jSon = new Gson().toJson(map);
		//}
		
		this.returnJson(response, jSon);
	}
	
	public void queryAttendanceByUserId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String userId = request.getParameter("userId");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
		AttendanceExample example = new AttendanceExample();
		example.or().andUseridEqualTo(Integer.parseInt(userId)).andDakdateEqualTo(new Date());
		List<Attendance> list = mapper.selectByExample(example);
		
		if(list.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			map.put("data", list.get(0));
			
			jSon = new Gson().toJson(map);
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	
	public void querySinginDataByUserId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String userId = request.getParameter("userId");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkTimeMapper mapper = sqlSession.getMapper(WorkTimeMapper.class);
		WorkTime obj = mapper.selectByUserId(Integer.parseInt(userId));
		
		if(obj != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			map.put("data", obj);
			
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			map.put("today_yearMonth", today.split("-")[0] + "-" + today.split("-")[1]);
			map.put("today_day", today.split("-")[2]);
			map.put("today_week", getWeekOfDate(new Date()));
			
			jSon = new Gson().toJson(map);
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String str = request.getParameter("attendance").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		SqlSession sqlSession = getSessionFactory().openSession();
		AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
		Attendance obj = mapper.selectByPrimaryKey(jsonObject.getInt("id"));
		
		if(obj != null) {
			obj.setRemark(jsonObject.getString("remark"));
			obj.setUpdatetime(new Date());
			
			int rtn = mapper.updateByPrimaryKey(obj);
			if(rtn > 0)
				jSon = "{\"success\": true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtnData = "{success:false}";
		String id = request.getParameter("id").toString();
		
		SqlSession sqlSession = this.getSessionFactory().openSession();;
		AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
		 
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
		
		int companyId = Integer.parseInt(request.getParameter("companyId") == null ? "-1" : request.getParameter("companyId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
		AttendanceExample example = new AttendanceExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<AttendanceEx> list = mapper.selectAll4Paging(start, limit, companyId);
		int i = 0;
		for (AttendanceEx obj : list) {
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
	
	public void signin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		String userId = request.getParameter("userId");
		
		AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
		Attendance obj = new Attendance();
		obj.setUserid(Integer.parseInt(userId));
		
		obj.setStarttime(new Date());
		if(checkIsChid(userId, obj.getStarttime())) {
			obj.setChid(1);
			map.put("chid", true);
		}
		
		obj.setIp("IP: " + request.getRemoteAddr());
		if(!checkIpValid(userId, obj.getIp())) {
			obj.setIperror(1);
			map.put("iperror", true);
		}
		
		obj.setCreatetime(new Date());
		obj.setUpdatetime(new Date());
		obj.setDakdate(new Date());
		
		int rtn = mapper.insert(obj);
		if(rtn > 0)
			map.put("success", true);
		
		sqlSession.close();
		
		try {
			this.returnJson(response, new Gson().toJson(map));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	private boolean checkIsZaot(String userId, Date signOutTime) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = this.getSessionFactory().openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.selectByPrimaryKey(Integer.parseInt(userId));
		
		WorkTimeMapper mapper2 = sqlSession.getMapper(WorkTimeMapper.class);
		WorkTime workTime = mapper2.selectByPrimaryKey(user.getWorktimeid());
		sqlSession.close();
		
		// 1970-01-01t01:00:00.000z
		String str = workTime.getEndtime().toUpperCase().replace("Z", " UTC");
		Date dbSigninTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z").parse(str);
		
		if(signOutTime.getHours() < dbSigninTime.getHours())
			return true;
		else {
			if(signOutTime.getHours() == dbSigninTime.getHours())
				if(signOutTime.getMinutes() < dbSigninTime.getMinutes())
					return true;
		}
		
		return false;
	}
	
	private boolean checkIsChid(String userId, Date signinTime) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = this.getSessionFactory().openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.selectByPrimaryKey(Integer.parseInt(userId));
		
		WorkTimeMapper mapper2 = sqlSession.getMapper(WorkTimeMapper.class);
		WorkTime workTime = mapper2.selectByPrimaryKey(user.getWorktimeid());
		sqlSession.close();
		
		// 1970-01-01t01:00:00.000z
		String str = workTime.getStarttime().toUpperCase().replace("Z", " UTC");
		Date dbSigninTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z").parse(str);
		
		if(signinTime.getHours() > dbSigninTime.getHours())
			return true;
		else {
			if(signinTime.getHours() == dbSigninTime.getHours())
				if(signinTime.getMinutes() > dbSigninTime.getMinutes())
					return true;
		}
		
		return false;
	}

	private boolean checkIpValid(String userId, String ip) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = this.getSessionFactory().openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.selectByPrimaryKey(Integer.parseInt(userId));
		
		CompanyMapper mapper2 = sqlSession.getMapper(CompanyMapper.class);
		Company company = mapper2.selectByPrimaryKey(user.getCompanyid());
		sqlSession.close();
		
		if(company != null)
			if(ip.equals(company.getIp()))
					return true;
		
		return false;
	}

	public void signout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		String userId = request.getParameter("userId");
		
		AttendanceMapper mapper = sqlSession.getMapper(AttendanceMapper.class);
		AttendanceExample example = new AttendanceExample();
		example.or().andUseridEqualTo(Integer.parseInt(userId))
		.andDakdateEqualTo(new Date());
		
		List<Attendance> list = mapper.selectByExample(example);
		
		if(list.size() > 0) {
			Attendance obj = list.get(0);
			obj.setEndtime(new Date());
			obj.setUpdatetime(new Date());
			
			if(checkIsZaot(userId, obj.getEndtime())) {
				obj.setZaot(1);
				map.put("chid", true);
			}
			
			obj.setIp("IP: " + request.getRemoteAddr());
			if(!checkIpValid(userId, obj.getIp())) {
				obj.setIperror(1);
				map.put("iperror", true);
			}
			
			if(mapper.updateByPrimaryKey(obj) > 0)
				map.put("success", true);
		}
		
		sqlSession.close();
		
		try {
			this.returnJson(response, new Gson().toJson(map));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
}