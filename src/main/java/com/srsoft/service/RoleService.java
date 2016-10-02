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
import com.srsoft.dao.RoleMapper;
import com.srsoft.model.Role;
import com.srsoft.model.RoleExample;
import com.srsoft.util.ServiceHelper;

public class RoleService extends ServiceHelper {
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String id = request.getParameter("id");
		String roleName = request.getParameter("roleName");
		roleName = URLDecoder.decode(URLDecoder.decode(roleName, "utf-8"), "utf-8");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
		Role obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
		if(obj != null) {
			obj.setRolename(roleName);
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
		RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
		 
		int rtn = mapper.deleteByPrimaryKey(Integer.parseInt(id));
		if(rtn >= 0) {
			rtnData = "{\"success\":true}";
		}
		
		sqlSession.close();
		
		returnJson(response, rtnData);
	}
	
	public void query(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		int companyId = Integer.parseInt(request.getParameter("companyId") == null ? "0" : request.getParameter("companyId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
		RoleExample example = new RoleExample();
		example.or().andIdNotEqualTo(9);
		int totalProperty  = mapper.countByExample(example);
		
		List<Role> list = mapper.selectAll4Paging(start, length, companyId);
		//List<Role> list = mapper.selectByExample(example);
		int i = 0;
		for (Role role : list) {
			role.setIndex(++i);
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
		
		String str = request.getParameter("role").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonStr: " + str);
		Role obj = (Role) JSONObject.toBean(JSONObject.fromObject(str), Role.class);
		
		RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
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