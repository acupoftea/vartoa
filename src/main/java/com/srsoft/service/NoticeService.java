package com.srsoft.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.srsoft.dao.NoticeMapper;
import com.srsoft.dao.NoticeTypeMapper;
import com.srsoft.model.Notice;
import com.srsoft.model.NoticeEx;
import com.srsoft.model.NoticeExample;
import com.srsoft.model.NoticeType;
import com.srsoft.util.ServiceHelper;

public class NoticeService extends ServiceHelper {
	
	public void query4employ(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		Integer companyId = Integer.parseInt(request.getParameter("companyId") == null ? "-1" : request.getParameter("companyId"));
		Integer noticeType = Integer.parseInt(request.getParameter("companyId") == null ? "-1" : request.getParameter("noticeType"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		NoticeExample example = new NoticeExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<NoticeEx> list = mapper.selectAll4employ(start, limit, companyId, noticeType);
		int i = 0;
		for (NoticeEx obj : list) {
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
	
	public void query4NoticeTypeList(HttpServletRequest request, HttpServletResponse response) {
		SqlSession sqlSession = getSessionFactory().openSession();
		
		NoticeTypeMapper mapper = sqlSession.getMapper(NoticeTypeMapper.class);
		List<NoticeType> list = mapper.selectByExample(null);
		
		sqlSession.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	String urlDecoder(String encodeData) {
		String rtnData = "";
		try {
			rtnData = URLDecoder.decode(URLDecoder.decode(encodeData, "utf-8"), "utf-8");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("urlDecoder: " + rtnData);
		
		return rtnData;
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String str = request.getParameter("notice") + "";
		str = urlDecoder(str);
		System.out.println("jsonStr: " + str);
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		SqlSession sqlSession = getSessionFactory().openSession();
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		Notice obj = mapper.selectByPrimaryKey(jsonObject.getInt("id"));
		
		if(obj != null) {
			String tmp = urlDecoder(jsonObject.getString("content"));
			obj.setContent(tmp);
			obj.setNoticetype(jsonObject.getInt("noticetype"));
			obj.setTitle(jsonObject.getString("title"));
			
			int rtn = mapper.updateByPrimaryKey(obj);
			if(rtn >= 0)
				jSon = "{\"success\": true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtnData = "{\"success\":false}";
		String id = request.getParameter("id").toString();
		
		SqlSession sqlSession = this.getSessionFactory().openSession();;
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		 
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
		Integer companyId = Integer.parseInt(request.getParameter("companyId") == null ? "-1" : request.getParameter("companyId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		NoticeExample example = new NoticeExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<NoticeEx> list = mapper.selectAll4Paging(start, limit, companyId);
		int i = 0;
		for (NoticeEx obj : list) {
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
	
	public void create(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String jSon = "{\"success\": false}";
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		String str = request.getParameter("notice");
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonStr: " + str);
		
		JSONObject jsonObject = JSONObject.fromObject(str);
		NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
		Notice obj = new Notice();
		
		String tmp = urlDecoder(jsonObject.getString("content"));
		obj.setContent(tmp);
		
		obj.setCreatetime(new Date());
		obj.setNoticetype(jsonObject.getInt("noticetype"));
		obj.setTitle(jsonObject.getString("title"));
		obj.setCompanyid(jsonObject.getInt("companyId"));
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