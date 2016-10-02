package com.srsoft.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.qiniu.util.Auth;
import com.srsoft.dao.CompanyMapper;
import com.srsoft.dao.DeparmentMapper;
import com.srsoft.dao.ModelMapper;
import com.srsoft.dao.PowerMapper;
import com.srsoft.dao.RoleMapper;
import com.srsoft.dao.RolePowerMapper;
import com.srsoft.dao.SyslogMapper;
import com.srsoft.dao.UserMapper;
import com.srsoft.model.Company;
import com.srsoft.model.Deparment;
import com.srsoft.model.Model;
import com.srsoft.model.Power;
import com.srsoft.model.PowerEx;
import com.srsoft.model.Role;
import com.srsoft.model.RolePowerExample;
import com.srsoft.model.RolePowerKey;
import com.srsoft.model.Syslog;
import com.srsoft.model.User;
import com.srsoft.model.UserEx;
import com.srsoft.model.UserExample;
import com.srsoft.util.ServiceHelper;

public class UserService extends ServiceHelper {
	public void test7niu(HttpServletRequest request, HttpServletResponse response) {
		// 设置好账号的ACCESS_KEY和SECRET_KEY
		String ACCESS_KEY = "cd49_Oa7kxvx8ebzyqSQmBG8ISYTJTp5Vi6e284t";
		String SECRET_KEY = "3WJoNPdOGHDiFCzV358uPqiBozEo0bKjEel94t8h";
		// 要上传的空间
		String bucketname = "test";

		// 密钥配置
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		String rtnData = auth.uploadToken(bucketname);
		System.out.println("token: " + rtnData);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	public void queryUserById(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter("selectUserId") == null ? "0" : request.getParameter("selectUserId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.selectByPrimaryKey(userId);
		
		sqlSession.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", user);
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	public void queryUsersByIds(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		String[] _ids = ids.split(",");
		//int ids = Integer.parseInt(request.getParameter("ids") == null ? "0" : request.getParameter("ids"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		
		String userNames = "";
		for(String id : _ids) {
			int nid = 0;
			if(id != null) {
				nid = Integer.parseInt(id);
				User user = mapper.selectByPrimaryKey(nid);
				userNames += user.getUsername() + ",";
			}
		}
		
		sqlSession.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", userNames);
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	public void query4Company(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		
		int companyId = Integer.parseInt(request.getParameter("companyId") == null ? "-1" : request.getParameter("companyId"));
		
		String orderColumn = request.getParameter("order[0][column]");
		String orderDir = request.getParameter("order[0][dir]");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		UserExample example = new UserExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<UserEx> list = mapper.selectByCompanyId4Paging(start, limit, companyId, orderColumn, orderDir);
		//List<User> list = mapper.selectByExample(example);
		int i = 0;
		for (UserEx obj : list) {
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
	
	public void changePsw(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("newPsw");
		
		SqlSession sqlSession = getSessionFactory().openSession(); 
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User ue = mapper.selectByPrimaryKey(Integer.parseInt(userId));
		ue.setPassword(password);
		
		int rtn = mapper.updateByPrimaryKey(ue);
		sqlSession.close();
		
		if(rtn >= 0) {
			map.put("success", true);
		}
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	String queryRoleNameById(int id) {
		String rtn = "";
		SqlSession sqlSession = getSessionFactory().openSession();
		RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
		Role role = mapper.selectByPrimaryKey(id);
		if(role != null)
			rtn = role.getRolename();
		
		sqlSession.close();
		
		return rtn;
	}
	
	String queryDepartmentNameById(int id) {
		String rtn = "";
		SqlSession sqlSession = getSessionFactory().openSession();
		DeparmentMapper mapper = sqlSession.getMapper(DeparmentMapper.class);
		Deparment deparment = mapper.selectByPrimaryKey(id);
		if(deparment != null)
			rtn = deparment.getName();
		
		sqlSession.close();
		
		return rtn;
	}
	
	String queryCompanyNameById(int id) {
		String rtn = "";
		SqlSession sqlSession = getSessionFactory().openSession();
		CompanyMapper mapper = sqlSession.getMapper(CompanyMapper.class);
		Company company = mapper.selectByPrimaryKey(id);
		if(company != null)
			rtn = company.getCompanyname();
		
		sqlSession.close();
		
		return rtn;
	}
	
	void writeLog(String ...data) {
		SqlSession sqlSession = getSessionFactory().openSession();
		SyslogMapper mapper = sqlSession.getMapper(SyslogMapper.class);
		
		Syslog syslog = new Syslog();
		syslog.setCreatetime(new Date());
		
		if(data.length > 0)
			syslog.setData1(data[0]);
		if(data.length > 1)
			syslog.setData2(data[1]);
		if(data.length > 2)
			syslog.setData3(data[2]);
		if(data.length > 3)
			syslog.setData4(data[3]);
		
		mapper.insert(syslog);
		sqlSession.close();
	}
	
	public void syslogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberPsw = request.getParameter("rememberPsw");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		UserEx user = mapper.selectByUnPw(userName, password);
		
		if(user != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			if(rememberPsw != null && rememberPsw.equals("on")) {
				Cookie cookie = new Cookie("loginName", user.getLoginname());
				cookie.setMaxAge(3600 * 24 * 15);
				response.addCookie(cookie);
				
				cookie = new Cookie("password", user.getPassword());
				cookie.setMaxAge(3600 * 24 * 15);
				response.addCookie(cookie);
				
			} else {
				Cookie cookie = new Cookie("loginName", user.getLoginname());
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				
				cookie = new Cookie("password", user.getPassword());
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
			map.put("userId", user.getId());
			map.put("userName", user.getUsername());
			map.put("roleName", user.getRoleName());
			map.put("companyId", user.getCompanyid());
			map.put("companyName", user.getCompanyName());
			map.put("departmentName", user.getDepartmentName());
			
			writeLog("登录系统", user.getUsername(), request.getRemoteAddr());
			
			request.getSession().setAttribute("userMap", map);
			
			List<PowerEx> powerExList = mapper.queryUserPowerExByRole(user.getRoleid());
			
			Map<String, Object> userPowerMap = new HashMap<String, Object>();
			if(powerExList.size() > 0) {
				userPowerMap.put("faterPower", powerExList.get(0));
				powerExList.remove(0);
				userPowerMap.put("sonPowerList", powerExList);
			}
			
			request.getSession().setAttribute("userPowerMap", userPowerMap);
			request.getRequestDispatcher("main.ftl").forward(request, response);
		}
		else {
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			System.out.println("用户名或密码错误, 跳转到index.jsp");
		}
		
		sqlSession.close();
	}
	
	private Model queryModelById(int modelId) {
		Model obj = null;
		
		SqlSession sqlSession = getSessionFactory().openSession();
		ModelMapper mapper = sqlSession.getMapper(ModelMapper.class);
		obj = mapper.selectByPrimaryKey(modelId);
		sqlSession.close();
		
		return obj;
	}

	private List<Power> queryUserPowerListByRoleId(Integer roleId) {
		List<Power> list = new ArrayList<Power>();
		
		SqlSession sqlSession = getSessionFactory().openSession();
		RolePowerMapper mapper = sqlSession.getMapper(RolePowerMapper.class);
		RolePowerExample example = new RolePowerExample();
		example.or().andRoleEqualTo(roleId);
		
		List<RolePowerKey> listRolePower = mapper.selectByExample(example);
		for (RolePowerKey rolePower : listRolePower) {
			int powerId = rolePower.getPower();
			list.add(queryPowerById(powerId));
		}
		
		sqlSession.close();
		
		return list;
	}

	private Power queryPowerById(int powerId) {
		Power power = null;
		
		SqlSession sqlSession = getSessionFactory().openSession();
		PowerMapper mapper = sqlSession.getMapper(PowerMapper.class);
		power = mapper.selectByPrimaryKey(powerId);
		sqlSession.close();
		
		return power;
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String str = request.getParameter("user").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonStr: " + str);
		User obj = (User) JSONObject.toBean(JSONObject.fromObject(str), User.class);
		
		SqlSession sqlSession = getSessionFactory().openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		int rtn = mapper.updateByPrimaryKey(obj);
		sqlSession.close();
		
		if(rtn > 0)
			jSon = "{\"success\": true}";
		
		this.returnJson(response, jSon);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtnData = "{\"success\":false}";
		String id = request.getParameter("id").toString();
		
		SqlSession sqlSession = this.getSessionFactory().openSession();;
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		 
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
		
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		UserExample example = new UserExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<UserEx> list = mapper.selectAll4Paging(start, limit);
		//List<User> list = mapper.selectByExample(example);
		int i = 0;
		for (UserEx obj : list) {
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
		
		String str = request.getParameter("user").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User obj = (User) JSONObject.toBean(jsonObject, User.class);
		obj.setPassword("123456");
		obj.setCreatetime(new Date());
		obj.setUpdatetime(new Date());
		
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