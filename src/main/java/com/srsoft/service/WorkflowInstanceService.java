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
import com.srsoft.dao.LeaveMapper;
import com.srsoft.dao.UserDefineFormDetailMapper;
import com.srsoft.dao.UserDefineFormInstanceMapper;
import com.srsoft.dao.UserMapper;
import com.srsoft.dao.WorkflowInstanceMapper;
import com.srsoft.dao.WorkflowLogMapper;
import com.srsoft.dao.WorkflowMapper;
import com.srsoft.dao.WorkflowNodeMapper;
import com.srsoft.model.Leave;
import com.srsoft.model.User;
import com.srsoft.model.UserDefineFormDetail;
import com.srsoft.model.UserDefineFormDetailExample;
import com.srsoft.model.UserDefineFormInstance;
import com.srsoft.model.UserDefineFormInstanceExample;
import com.srsoft.model.Workflow;
import com.srsoft.model.WorkflowForward;
import com.srsoft.model.WorkflowInstance;
import com.srsoft.model.WorkflowInstanceEx;
import com.srsoft.model.WorkflowInstanceExample;
import com.srsoft.model.WorkflowLog;
import com.srsoft.model.WorkflowLogEx;
import com.srsoft.model.WorkflowNode;
import com.srsoft.model.WorkflowNodeExample;
import com.srsoft.util.ServiceHelper;

public class WorkflowInstanceService extends ServiceHelper {
	public void reSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String id = request.getParameter("wfiId");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstance obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
		if(obj != null) {
			obj.setActivenode(queryWorkflowStartNode(obj.getWorkflowid()));
			obj.setStatus(1);
			obj.setUpdatetime(new Date());
			
			int rtn = mapper.updateByPrimaryKey(obj);
			if(rtn > 0)
				jSon = "{\"success\": true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void queryHistoryAuditData(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		Integer wfiId = Integer.parseInt(request.getParameter("wfiId") == null ? "-1" : request.getParameter("wfiId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowLogMapper mapper = sqlSession.getMapper(WorkflowLogMapper.class);
		List<WorkflowLogEx> list = mapper.selectWorkflowLogByWfi(start, limit, wfiId);
		
		int i = 0;
		for (WorkflowLogEx obj : list) {
			obj.setIndex(++i);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("recordsTotal", list.size());
		map.put("recordsFiltered", list.size());
		
		sqlSession.close();
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	public void recover4recycleBin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String id = request.getParameter("id");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstance obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
		if(obj != null) {
			obj.setStatus(0);
			int rtn = mapper.updateByPrimaryKey(obj);
			if(rtn > 0)
				jSon = "{\"success\": true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void query4recycleBin(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		
		int userId = Integer.parseInt(request.getParameter("userId") == null ? "-1" : request.getParameter("userId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstanceExample example = new WorkflowInstanceExample();
		example.or().andStatusEqualTo(6);
		int totalProperty  = mapper.countByExample(example);
		
		List<WorkflowInstanceEx> list = mapper.selectAll4RecycleBin(start, limit, userId);
		int i = start;
		for (WorkflowInstanceEx obj : list) {
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
	
	public void move2recycleBin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String id = request.getParameter("id");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstance obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
		if(obj != null && obj.getCanbeedit() == 1) {
			obj.setStatus(6);
			int rtn = mapper.updateByPrimaryKey(obj);
			if(rtn > 0)
				jSon = "{\"success\": true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void query4archiving(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		
		int userId = Integer.parseInt(request.getParameter("userId") == null ? "-1" : request.getParameter("userId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstanceExample example = new WorkflowInstanceExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<WorkflowInstanceEx> list = mapper.selectAll4Archiving(start, limit, userId);
		int i = 0;
		for (WorkflowInstanceEx obj : list) {
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
	
	public void archiving(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String id = request.getParameter("id");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstance obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
		if(obj != null) {
			obj.setStatus(5);
			int rtn = mapper.updateByPrimaryKey(obj);
			if(rtn > 0)
				jSon = "{\"success\": true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	WorkflowForward forward(WorkflowInstance wfi) {
		WorkflowForward wf = new WorkflowForward();
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowNodeMapper mapper = sqlSession.getMapper(WorkflowNodeMapper.class);
		WorkflowNode currentWorkflowNode = mapper.selectByPrimaryKey(wfi.getActivenode());
		WorkflowNode nextWorkflowNode = mapper.selectByPrimaryKey(currentWorkflowNode.getNextlink());
		
		wf.setNextNode(currentWorkflowNode.getNextlink());
		
		if(nextWorkflowNode.getSequence().equals(100))
			wf.setEndNode(true);
		
		sqlSession.close();
		
		return wf;
	}
	
	WorkflowForward backward(WorkflowInstance wfi) {
		WorkflowForward wf = new WorkflowForward();
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowNodeMapper mapper = sqlSession.getMapper(WorkflowNodeMapper.class);
		WorkflowNode currentWorkflowNode = mapper.selectByPrimaryKey(wfi.getActivenode());
		
		// 如果第一个结点就被驳回则原地不动
		if(!currentWorkflowNode.getSequence().equals(1))
			wf.setPreNode(currentWorkflowNode.getPrelink());
		else
			wf.setPreNode(wfi.getActivenode());
		
		sqlSession.close();
		
		return wf;
	}
	
	void writeWorkflowLog(int operator, int workflowInstance, int workflownodeid, String action, int isForward, String remark) {
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowLogMapper mapper = sqlSession.getMapper(WorkflowLogMapper.class);
		
		WorkflowLog workflowLog = new WorkflowLog();
		workflowLog.setWorkflowinstance(workflowInstance);
		workflowLog.setWorkflownodeid(workflownodeid);
		workflowLog.setAction(action);
		workflowLog.setIsforward(isForward);
		workflowLog.setCreatetime(new Date());
		workflowLog.setRemark(remark);
		workflowLog.setOperator(operator);
		
		mapper.insert(workflowLog);
		
		sqlSession.close();
	}
	
	public void audit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{\"success\": false}";
		
		String str = request.getParameter("audit").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstance wfi = mapper.selectByPrimaryKey(jsonObject.getInt("id"));
		
		// 已执行
		if(jsonObject.getInt("ispass") == 2) {
			WorkflowForward wf = forward(wfi);
			if(wf.isEndNode()) {
				wfi.setActivenode(wf.getNextNode());
				wfi.setStatus(4);
				
				writeWorkflowLog(jsonObject.getInt("auditer"), wfi.getId(), wfi.getActivenode(), "流程结束", 1, 
						jsonObject.get("opinion") == null ? "" : jsonObject.getString("opinion"));
			}
			else {
				writeWorkflowLog(jsonObject.getInt("auditer"), wfi.getId(), wfi.getActivenode(), "已执行审批内容", 1, 
						jsonObject.get("opinion") == null ? "" : jsonObject.getString("opinion"));
				
				wfi.setActivenode(wf.getNextNode());
				wfi.setStatus(7);
			}
		}
		
		// 通过
		if(jsonObject.getInt("ispass") == 1) {
			WorkflowForward wf = forward(wfi);
			if(wf.isEndNode()) {
				wfi.setActivenode(wf.getNextNode());
				wfi.setStatus(4);
				
				writeWorkflowLog(jsonObject.getInt("auditer"), wfi.getId(), wfi.getActivenode(), "流程结束", 1, 
						jsonObject.get("opinion") == null ? "" : jsonObject.getString("opinion"));
			}
			else {
				writeWorkflowLog(jsonObject.getInt("auditer"), wfi.getId(), wfi.getActivenode(), "审核通过", 1, 
						jsonObject.get("opinion") == null ? "" : jsonObject.getString("opinion"));
				
				wfi.setActivenode(wf.getNextNode());
				wfi.setStatus(1);
			}
		}
		
		// 驳回
		if(jsonObject.getInt("ispass") == 0) {
			WorkflowForward wf = backward(wfi);
			if(wf.isStartNode()) {
				wfi.setActivenode(wf.getPreNode());
				wfi.setCanbeedit(1);
				wfi.setStatus(3);
				
				writeWorkflowLog(jsonObject.getInt("auditer"), wfi.getId(), wfi.getActivenode(), "已退回到开始结点", 0, 
						jsonObject.get("opinion") == null ? "" : jsonObject.getString("opinion"));
				
			} else {
				writeWorkflowLog(jsonObject.getInt("auditer"), wfi.getId(), wfi.getActivenode(), "审核失败", 0, 
						jsonObject.get("opinion") == null ? "" : jsonObject.getString("opinion"));
				
				wfi.setActivenode(wf.getPreNode());
				wfi.setStatus(3);
				wfi.setCanbeedit(1);
			}
		}
		
		if(wfi != null) {
			wfi.setUpdatetime(new Date());
			int rtn = mapper.updateByPrimaryKey(wfi);
			if(rtn > 0)
				jSon = "{\"success\": true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	Leave queryLeaveById(int id) {
		SqlSession sqlSession = getSessionFactory().openSession();
		LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
		Leave leave = mapper.selectByPrimaryKey(id);
		sqlSession.close();
		
		return leave;
	}
	
	Object invokeMethod(Object owner, String methodName) throws Exception {  
	     return owner.getClass().getMethod(methodName).invoke(owner);  
	}
	
	String queryUserNameById(int id) {
		SqlSession sqlSession = getSessionFactory().openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.selectByPrimaryKey(id);
		sqlSession.close();
		
		return user.getUsername();
	}
	
	public void queryAuditData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> jSonMap = new HashMap<String, Object>();
		jSonMap.put("success", false);
		
		Integer wfiId = Integer.parseInt(request.getParameter("wfiId") == null ? "-1" : request.getParameter("wfiId"));
		
		// 查询流程实例
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstanceEx wfiex = mapper.selectWorkflowInstanceExById(wfiId);
		
		// 查询工作流
		WorkflowMapper mapper2 = sqlSession.getMapper(WorkflowMapper.class);
		Workflow wf = mapper2.selectByPrimaryKey(wfiex.getWorkflowid());
		
		// 查询用户自定义表单实例
		UserDefineFormInstanceMapper mapper3 = sqlSession.getMapper(UserDefineFormInstanceMapper.class);
		UserDefineFormInstanceExample example3 = new UserDefineFormInstanceExample();
		example3.or().andUdfidEqualTo(wf.getUdfid()).andWfiidEqualTo(wfiex.getId());
		List<UserDefineFormInstance> list = mapper3.selectByExample(example3);
		UserDefineFormInstance udfi = null;
		if(list.size() > 0)
			udfi = list.get(0);
		
		if(list.size() > 0) {
			// 查询表单字段
			UserDefineFormDetailMapper mapper4 = sqlSession.getMapper(UserDefineFormDetailMapper.class);
			UserDefineFormDetailExample example4 = new UserDefineFormDetailExample();
			example4.or().andUdfidEqualTo(wf.getUdfid());
			example4.setOrderByClause("sequence");
			List<UserDefineFormDetail> list2 = mapper4.selectByExample(example4);
			
			// 组合字段和表单数据
			List<Map<String, Object>> list3 = new ArrayList<Map<String,Object>>();
			int index = 0;
			for (UserDefineFormDetail userDefineFormDetail : list2) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("label", userDefineFormDetail.getLabel());
				map.put("value", invokeMethod(udfi, "getArg" + index++));
				map.put("type", userDefineFormDetail.getWidgettype());
				
				list3.add(map);
			}
			
			WorkflowNodeMapper mapper5 = sqlSession.getMapper(WorkflowNodeMapper.class);
			WorkflowNodeExample example5 = new WorkflowNodeExample();
			example5.or().andWorkflowidEqualTo(wf.getId());
			example5.setOrderByClause("sequence");
			List<WorkflowNode> list5 = mapper5.selectByExample(example5);
			
			
			String auditStep = "";
			for (WorkflowNode workflowNode : list5) {
				auditStep += workflowNode.getNodename();
				if(workflowNode.getReceiver() != null)
					auditStep += " (" + queryUserNameById(workflowNode.getReceiver()) + ")";
				if(workflowNode.getSequence() != 100)
					auditStep += " >> ";
			}
			
			jSonMap.put("auditStep", auditStep);
			 
			jSonMap.put("wfi", wfiex);
			jSonMap.put("data", list3);
			jSonMap.put("success", true);
		}
		
		sqlSession.close();
		
		String rtnData = new Gson().toJson(jSonMap);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	public void queryAuditList(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		
		int userId = Integer.parseInt(request.getParameter("userId") == null ? "-1" : request.getParameter("userId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstanceExample example = new WorkflowInstanceExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<WorkflowInstanceEx> list = mapper.queryAuditList(start, limit, userId);
		int i = 0;
		for (WorkflowInstanceEx obj : list) {
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
	
	public void queryCcList(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		
		int userId = Integer.parseInt(request.getParameter("userId") == null ? "-1" : request.getParameter("userId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstanceExample example = new WorkflowInstanceExample();
		example.or().andStatusNotEqualTo(6);
		int totalProperty  = mapper.countByExample(example);
		
		List<WorkflowInstanceEx> list = mapper.queryCcList(start, limit, userId);
		int i = start;
		for (WorkflowInstanceEx obj : list) {
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
	
	int queryWorkflowStartNode(int workflowId) {
		int startNode = -1;
		
		SqlSession sqlSession = this.getSessionFactory().openSession();
		WorkflowNodeMapper mapper = sqlSession.getMapper(WorkflowNodeMapper.class);
		WorkflowNodeExample example = new WorkflowNodeExample();
		example.or().andWorkflowidEqualTo(workflowId).andSequenceEqualTo(1);
		List<WorkflowNode> list = mapper.selectByExample(example);
		if(list.size() > 0)
			startNode = list.get(0).getId();
		sqlSession.close();
		
		return startNode;
	}
	
	public int startWorkflow(int workflowId, int sender, int attachmentId, String summary) {
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstance obj = new WorkflowInstance();
		
		obj.setWorkflowid(workflowId);
		obj.setSender(sender);
		obj.setActivenode(queryWorkflowStartNode(workflowId));
		obj.setAttachmentid(attachmentId);
		obj.setStatus(1);
		obj.setCreatetime(new Date());
		obj.setUpdatetime(new Date());
		obj.setCanbeedit(1);
		obj.setSummary(summary);
		
		int rtn = mapper.insert(obj);
		if(rtn > 0)
			return obj.getId();
		
		sqlSession.close();
		
		return 0;
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jSon = "{success: false}";
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstance obj = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
		if(obj != null) {
			
			int rtn = mapper.updateByPrimaryKey(obj);
			if(rtn > 0)
				jSon = "{success: true}";
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtnData = "{\"success\":false}";
		String id = request.getParameter("id").toString();
		
		SqlSession sqlSession = this.getSessionFactory().openSession();;
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstance wfi = mapper.selectByPrimaryKey(Integer.parseInt(id));
		
		if(wfi.getCanbeedit() == 1) {
			int rtn = mapper.deleteByPrimaryKey(Integer.parseInt(id));
			if(rtn >= 0) {
				rtnData = "{\"success\":true}";
			}
		}
		
		sqlSession.close();
		
		returnJson(response, rtnData);
	}
	
	public void query(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "0" : request.getParameter("length"));
		
		int userId = Integer.parseInt(request.getParameter("userId") == null ? "-1" : request.getParameter("userId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstanceExample example = new WorkflowInstanceExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<WorkflowInstanceEx> list = mapper.selectAll4Paging(start, limit, userId);
		//List<WorkflowInstance> list = mapper.selectByExample(example);
		int i = 0;
		for (WorkflowInstanceEx obj : list) {
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
		
		String str = request.getParameter("data").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonData: " + str);
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		UserDefineFormInstanceMapper mapper = sqlSession.getMapper(UserDefineFormInstanceMapper.class);
		UserDefineFormInstance udfi = new UserDefineFormInstance();
		udfi.setUdfid(jsonObject.getInt("udfid"));
		
		JSONObject jsonObject2 = jsonObject.getJSONObject("formdata");
		if(jsonObject2.get("arg0") != null)
			udfi.setArg0(jsonObject2.getString("arg0"));
		if(jsonObject2.get("arg1") != null)
			udfi.setArg1(jsonObject2.getString("arg1"));
		if(jsonObject2.get("arg2") != null)
			udfi.setArg2(jsonObject2.getString("arg2"));
		if(jsonObject2.get("arg3") != null)
			udfi.setArg3(jsonObject2.getString("arg3"));
		if(jsonObject2.get("arg4") != null)
			udfi.setArg4(jsonObject2.getString("arg4"));
		if(jsonObject2.get("arg5") != null)
			udfi.setArg5(jsonObject2.getString("arg5"));
		if(jsonObject2.get("arg6") != null)
			udfi.setArg6(jsonObject2.getString("arg6"));
		if(jsonObject2.get("arg7") != null)
			udfi.setArg7(jsonObject2.getString("arg7"));
		if(jsonObject2.get("arg8") != null)
			udfi.setArg8(jsonObject2.getString("arg8"));
		if(jsonObject2.get("arg9") != null)
			udfi.setArg9(jsonObject2.getString("arg9"));
			
		
		mapper.insert(udfi);
		
		int rtn = this.startWorkflow(jsonObject.getInt("workflowid"), jsonObject.getInt("userid"), udfi.getId(), jsonObject.getString("summary"));
		if(rtn > 0) {
			udfi.setWfiid(rtn);
			rtn = mapper.updateByPrimaryKey(udfi);
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