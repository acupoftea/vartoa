package com.srsoft.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.srsoft.dao.UserDefineFormDetailMapper;
import com.srsoft.dao.UserDefinedFormMapper;
import com.srsoft.model.UserDefineFormDetail;
import com.srsoft.model.UserDefineFormDetailExample;
import com.srsoft.model.UserDefinedForm;
import com.srsoft.model.UserDefinedFormEx;
import com.srsoft.util.ServiceHelper;

public class UserDefinedFormService extends ServiceHelper {
	public void queryUdfList(HttpServletRequest request, HttpServletResponse response) {
		int udfId = Integer.parseInt(request.getParameter("udfId") == null ? "-1" : request.getParameter("udfId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		UserDefineFormDetailMapper mapper = sqlSession.getMapper(UserDefineFormDetailMapper.class);
		UserDefineFormDetailExample example = new UserDefineFormDetailExample();
		example.or().andUdfidEqualTo(udfId);
		example.setOrderByClause("sequence");
		
		List<UserDefineFormDetail> list = mapper.selectByExample(example);
		
		sqlSession.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("data", list);
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	
	public void deleteAttachment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		SqlSession sqlSession = this.getSessionFactory().openSession();
		UserDefinedFormMapper mapper = sqlSession.getMapper(UserDefinedFormMapper.class);
		UserDefinedForm obj = mapper.selectByPrimaryKey(id);
		obj.setAttachmenturl(null);
		int rtn = mapper.updateByPrimaryKey(obj);
		if(rtn > 0)
			jSon = "{\"success\": true}";
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String str = request.getParameter("formList").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonStr: " + str);
		
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		UserDefinedFormMapper mapper = sqlSession.getMapper(UserDefinedFormMapper.class);
		UserDefinedForm obj = mapper.selectByPrimaryKey(jsonObject.getInt("id"));
		obj.setName(jsonObject.getString("name"));
		String attachmentUrl = jsonObject.getString("attachmenturl").toUpperCase();
		obj.setAttachmenturl(attachmentUrl);
		int rtn = mapper.updateByPrimaryKey(obj);
		
		if(rtn > 0) {
			UserDefineFormDetailMapper mapper2 = sqlSession.getMapper(UserDefineFormDetailMapper.class);
			UserDefineFormDetailExample example = new UserDefineFormDetailExample();
			example.or().andUdfidEqualTo(obj.getId());
			mapper2.deleteByExample(example);
			
			JSONArray array = jsonObject.getJSONArray("formlist");
			for(int i = 0; i < array.size(); i++)
			{
				JSONObject jsonObject2 = array.getJSONObject(i);
				UserDefineFormDetail udfd = new UserDefineFormDetail();
				udfd.setUdfid(obj.getId());
				udfd.setRequirefield(jsonObject2.getInt("isrequirefield"));
				udfd.setLabel(jsonObject2.getString("controllabel"));
				udfd.setWidgettype(jsonObject2.getInt("controltype"));
				udfd.setControlvalue(jsonObject2.getString("controlvalue"));
				udfd.setSequence(jsonObject2.getInt("sequence"));
				rtn = mapper2.insert(udfd);
				
			}
			
			if(rtn > 0)
				jSon = "{\"success\": true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtnData = "{\"success\":false}";
		String id = request.getParameter("id").toString();
		
		SqlSession sqlSession = this.getSessionFactory().openSession();
		UserDefinedFormMapper mapper = sqlSession.getMapper(UserDefinedFormMapper.class);
		 
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
		
		UserDefinedFormMapper mapper = sqlSession.getMapper(UserDefinedFormMapper.class);
		
		List<UserDefinedFormEx> list = mapper.selectAll4Paging(start, limit, companyId);
		int i = 0;
		for (UserDefinedFormEx obj : list) {
			obj.setIndex(++i);
		}
		
		sqlSession.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("recordsTotal", list.size());
		map.put("recordsFiltered", list.size());
		
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
		
		String str = request.getParameter("formList").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonStr: " + str);
		
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		UserDefinedFormMapper mapper = sqlSession.getMapper(UserDefinedFormMapper.class);
		UserDefinedForm obj = new UserDefinedForm();
		obj.setName(jsonObject.getString("name"));
		obj.setCompanyid(jsonObject.getInt("companyid"));
		
		String attachmentUrl = "";
		if(jsonObject.get("attachmenturl")!= null) {
			attachmentUrl = jsonObject.getString("attachmenturl").toUpperCase();
			obj.setAttachmenturl(attachmentUrl);
		}
		
		int rtn = mapper.insert(obj);
		if(rtn > 0) {
			UserDefineFormDetailMapper mapper2 = sqlSession.getMapper(UserDefineFormDetailMapper.class);
			
			JSONArray array = jsonObject.getJSONArray("formlist");
			for(int i = 0; i < array.size(); i++)
			{
				JSONObject jsonObject2 = array.getJSONObject(i);
				UserDefineFormDetail udfd = new UserDefineFormDetail();
				udfd.setUdfid(obj.getId());
				udfd.setRequirefield(jsonObject2.getInt("isrequirefield"));
				udfd.setLabel(jsonObject2.getString("controllabel"));
				udfd.setWidgettype(jsonObject2.getInt("controltype"));
				udfd.setControlvalue(jsonObject2.getString("controlvalue"));
				udfd.setSequence(jsonObject2.getInt("sequence"));
				rtn = mapper2.insert(udfd);
				
			}
			
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