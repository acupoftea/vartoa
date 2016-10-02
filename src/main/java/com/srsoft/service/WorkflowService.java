package com.srsoft.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
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
import com.srsoft.dao.UserMapper;
import com.srsoft.dao.WorkflowInstanceMapper;
import com.srsoft.dao.WorkflowMapper;
import com.srsoft.dao.WorkflowNodeMapper;
import com.srsoft.dao.WorkflowReceiver4ccMapper;
import com.srsoft.model.User;
import com.srsoft.model.UserDefineFormDetail;
import com.srsoft.model.UserDefineFormDetailExample;
import com.srsoft.model.UserDefinedForm;
import com.srsoft.model.Workflow;
import com.srsoft.model.WorkflowEx;
import com.srsoft.model.WorkflowExample;
import com.srsoft.model.WorkflowInstance;
import com.srsoft.model.WorkflowNode;
import com.srsoft.model.WorkflowNodeEx;
import com.srsoft.model.WorkflowNodeExample;
import com.srsoft.model.WorkflowReceiver4cc;
import com.srsoft.model.WorkflowReceiver4ccExample;
import com.srsoft.util.ServiceHelper;

public class WorkflowService extends ServiceHelper {
	String queryCcerNameByIds(String ids) {
		String userNames = "";
		SqlSession sqlSession = getSessionFactory().openSession();
		
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		String[] _ids = ids.split(",");
		for(String id : _ids) {
			if(id != null && !id.equals("")) {
				User user = mapper.selectByPrimaryKey(Integer.parseInt(id));
				if(user != null)
					userNames += user.getUsername() + ",";
			}
		}
		
		sqlSession.close();
		
		return userNames;
	}
	
	
	String queryCcerIdList(int workFlowId, int workFlowNodeId) {
		String ids = "";
		SqlSession sqlSession = getSessionFactory().openSession();
		
		WorkflowReceiver4ccMapper mapper = sqlSession.getMapper(WorkflowReceiver4ccMapper.class);
		WorkflowReceiver4ccExample example = new WorkflowReceiver4ccExample();
		example.or().andWorkflowidEqualTo(workFlowId).andWorkflownodeidEqualTo(workFlowNodeId);
		List<WorkflowReceiver4cc> list = mapper.selectByExample(example);
		
		for (WorkflowReceiver4cc workflowReceiver4cc : list) {
			ids += workflowReceiver4cc.getReceiver() + ",";
		}
		
		sqlSession.close();
		
		return ids;
	}
	
	public void queryworkflowNodeList(HttpServletRequest request, HttpServletResponse response) {
		int workflowId = Integer.parseInt(request.getParameter("workflowId") == null ? "-1" : request.getParameter("workflowId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		WorkflowNodeMapper mapper = sqlSession.getMapper(WorkflowNodeMapper.class);
		WorkflowNodeExample example = new WorkflowNodeExample();
		example.or().andWorkflowidEqualTo(workflowId);
		
		List<WorkflowNodeEx> list = mapper.queryWorkflowNodeListBywfId(workflowId);
		for (WorkflowNodeEx workflowNodeEx : list) {
			int workFlowId = workflowNodeEx.getWorkflowid();
			int workFlowNodeId = workflowNodeEx.getId();
			
			String ids = queryCcerIdList(workFlowId, workFlowNodeId);
			workflowNodeEx.setCcReceiverId(ids);
			workflowNodeEx.setCcName(queryCcerNameByIds(ids));
		}
		
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
	
	public void queryFromAndData4Edit(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		
		int wfiId = Integer.parseInt(request.getParameter("wfiId") == null ? "-1" : request.getParameter("wfiId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		WorkflowInstanceMapper mapper = sqlSession.getMapper(WorkflowInstanceMapper.class);
		WorkflowInstance wfi = mapper.selectByPrimaryKey(wfiId);
		
		WorkflowMapper mapper2 = sqlSession.getMapper(WorkflowMapper.class);
		Workflow wf = mapper2.selectByPrimaryKey(wfi.getWorkflowid());
		
		if(wf != null) {
			UserDefinedFormMapper mapper3 = sqlSession.getMapper(UserDefinedFormMapper.class);
			UserDefinedForm udf = mapper3.selectByPrimaryKey(wf.getUdfid());
			if(udf != null) {
				UserDefineFormDetailMapper mapper4 = sqlSession.getMapper(UserDefineFormDetailMapper.class);
				UserDefineFormDetailExample example = new UserDefineFormDetailExample();
				example.or().andUdfidEqualTo(udf.getId());
				example.setOrderByClause("sequence");
				List<UserDefineFormDetail> list =  mapper4.selectByExample(example);
				map.put("success", true);
				map.put("data", list);
				map.put("workflow", wf);
				
				WorkflowNodeMapper mapper5 = sqlSession.getMapper(WorkflowNodeMapper.class);
				WorkflowNodeExample example5 = new WorkflowNodeExample();
				example5.or().andWorkflowidEqualTo(wf.getId());
				List<WorkflowNode> list5 = mapper5.selectByExample(example5);
				
				String auditStep = "";
				for (WorkflowNode workflowNode : list5) {
					auditStep += workflowNode.getNodename();
					if(workflowNode.getReceiver() != null)
						auditStep += " (" + queryUserNameById(workflowNode.getReceiver()) + ")";
					if(workflowNode.getSequence() != 100)
						auditStep += " >> ";
				}
				
				map.put("auditStep", auditStep);
			}
		}
		
		sqlSession.close();
		
		String rtnData = new Gson().toJson(map);
		try {
			this.returnJson(response, rtnData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	String queryUserNameById(int id) {
		SqlSession sqlSession = getSessionFactory().openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.selectByPrimaryKey(id);
		sqlSession.close();
		
		return user.getUsername();
	}
	
	public void queryFromListByWorkflowId(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		
		int workflowId = Integer.parseInt(request.getParameter("workflowId") == null ? "-1" : request.getParameter("workflowId"));
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowMapper mapper = sqlSession.getMapper(WorkflowMapper.class);
		Workflow wf = mapper.selectByPrimaryKey(workflowId);
		
		if(wf != null) {
			UserDefinedFormMapper mapper2 = sqlSession.getMapper(UserDefinedFormMapper.class);
			UserDefinedForm udf = mapper2.selectByPrimaryKey(wf.getUdfid());
			if(udf != null) {
				UserDefineFormDetailMapper mapper3 = sqlSession.getMapper(UserDefineFormDetailMapper.class);
				UserDefineFormDetailExample example = new UserDefineFormDetailExample();
				example.or().andUdfidEqualTo(udf.getId());
				example.setOrderByClause("sequence");
				List<UserDefineFormDetail> list =  mapper3.selectByExample(example);
				map.put("success", true);
				map.put("data", list);
				map.put("udf_attachment", udf.getAttachmenturl());
				map.put("workflow", wf);
				
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
				
				map.put("auditStep", auditStep);
			}
		}
		
		sqlSession.close();
		
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
		
		String str = request.getParameter("workflow").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonStr: " + str);
		
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		SqlSession sqlSession = getSessionFactory().openSession();
		WorkflowMapper mapper = sqlSession.getMapper(WorkflowMapper.class);
		Workflow obj = mapper.selectByPrimaryKey(jsonObject.getInt("id"));
		
		if (obj != null) {
			obj.setName(jsonObject.getString("name"));
			obj.setUdfid(jsonObject.getInt("udfid"));

			int rtn = mapper.updateByPrimaryKey(obj);
			if (rtn > 0) {
				WorkflowNodeMapper mapper2 = sqlSession.getMapper(WorkflowNodeMapper.class);
				WorkflowNodeExample example = new WorkflowNodeExample();
				example.or().andWorkflowidEqualTo(obj.getId());
				rtn = mapper2.deleteByExample(example);

				if (rtn > 0) {
					WorkflowNode wn = new WorkflowNode();
					wn.setWorkflowid(obj.getId());
					wn.setNodename("未开始");
					wn.setSequence(0);
					rtn = mapper2.insert(wn);

					WorkflowReceiver4ccMapper mapper3 = sqlSession.getMapper(WorkflowReceiver4ccMapper.class);

					if (rtn > 0) {
						JSONArray array = jsonObject.getJSONArray("nodejson");
						int preNodeId = -1;
						for (int i = 0; i < array.size(); i++) {
							JSONObject jsonObject2 = array.getJSONObject(i);

							preNodeId = wn.getId();
							wn = new WorkflowNode();
							wn.setWorkflowid(obj.getId());
							wn.setNodename(jsonObject2.getString("nodename"));
							wn.setReceiver(jsonObject2.getInt("auditerid"));
							wn.setSequence(i + 1);
							wn.setPrelink(preNodeId);
							wn.setNextlink(preNodeId + 2);

							rtn = mapper2.insert(wn);
							
							if(jsonObject2.get("ccerid") != null) {
								String[] cceridList = jsonObject2.get("ccerid").toString().split(",");
								for(String _id : cceridList) {
									if(_id.equals(""))
										continue;
									
									WorkflowReceiver4cc wf4cc = new WorkflowReceiver4cc();
									wf4cc.setWorkflowid(obj.getId());
									wf4cc.setWorkflownodeid(wn.getId());
									wf4cc.setReceiver(Integer.parseInt(_id));
		
									rtn = mapper3.insert(wf4cc);
								}
							}
						}

						preNodeId = wn.getId();
						wn = new WorkflowNode();
						wn.setWorkflowid(obj.getId());
						wn.setNodename("已结束");
						wn.setSequence(100);
						wn.setPrelink(preNodeId);
						wn.setNextlink(preNodeId + 2);
						rtn = mapper2.insert(wn);
						if (rtn > 0)
							jSon = "{\"success\": true}";
					}

					jSon = "{\"success\": true}";
				}
			}
		}
		
		sqlSession.close();
		
		this.returnJson(response, jSon);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtnData = "{\"success\":false}";
		String id = request.getParameter("id").toString();
		
		SqlSession sqlSession = this.getSessionFactory().openSession();;
		WorkflowMapper mapper = sqlSession.getMapper(WorkflowMapper.class);
		 
		int rtn = mapper.deleteByPrimaryKey(Integer.parseInt(id));
		if(rtn >= 0) {
			rtnData = "{\"success\":true}";
		}
		
		sqlSession.close();
		
		returnJson(response, rtnData);
	}
	
	public void query(HttpServletRequest request, HttpServletResponse response) {
		int start = Integer.parseInt(request.getParameter("start") == null ? "0" : request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("length") == null ? "100" : request.getParameter("length"));
		
		int companyId = Integer.parseInt(request.getParameter("companyId") == null ? "-1" : request.getParameter("companyId"));
		
		SqlSession sqlSession = getSessionFactory().openSession();
		
		WorkflowMapper mapper = sqlSession.getMapper(WorkflowMapper.class);
		WorkflowExample example = new WorkflowExample();
		int totalProperty  = mapper.countByExample(example);
		
		List<WorkflowEx> list = mapper.selectAll4Paging(start, limit, companyId);
		int i = start;
		for (WorkflowEx obj : list) {
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
		
		String str = request.getParameter("workflow").toLowerCase();
		str = URLDecoder.decode(URLDecoder.decode(str, "utf-8"), "utf-8");
		System.out.println("jsonStr: " + str);
		
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		WorkflowMapper mapper = sqlSession.getMapper(WorkflowMapper.class);
		Workflow wf = new Workflow();
		wf.setName(jsonObject.getString("name"));
		//wf.setType(jsonObject.getInt("type"));
		wf.setType(0);
		wf.setCompanyid(jsonObject.getInt("companyid"));
		wf.setCreatetime(new Date());
		wf.setUpdatetime(new Date());
		
		int udfid = jsonObject.get("udfid") == null ? -1 : jsonObject.getInt("udfid");
		wf.setUdfid(udfid);
		
		int rtn = mapper.insert(wf);
		if(rtn > 0) {
			WorkflowNodeMapper mapper2 = sqlSession.getMapper(WorkflowNodeMapper.class);
			WorkflowNode wn = new WorkflowNode();
			wn.setWorkflowid(wf.getId());
			wn.setNodename("未开始");
			wn.setSequence(0);
			rtn = mapper2.insert(wn);
			
			WorkflowReceiver4ccMapper mapper3 = sqlSession.getMapper(WorkflowReceiver4ccMapper.class);
			
			if(rtn > 0) {
				JSONArray array = jsonObject.getJSONArray("nodejson");
				int preNodeId = -1;
				for(int i = 0; i < array.size(); i++)
				{
					JSONObject jsonObject2 = array.getJSONObject(i);
					
					preNodeId = wn.getId();
					wn = new WorkflowNode();
					wn.setWorkflowid(wf.getId());
					wn.setNodename(jsonObject2.getString("nodename"));
					wn.setReceiver(jsonObject2.getInt("auditerid"));
					wn.setSequence(i + 1);
					wn.setPrelink(preNodeId);
					wn.setNextlink(preNodeId + 2);
					
					rtn = mapper2.insert(wn);
					
					WorkflowReceiver4cc wf4cc = new WorkflowReceiver4cc();
					wf4cc.setWorkflowid(wf.getId());
					wf4cc.setWorkflownodeid(wn.getId());
					wf4cc.setReceiver(jsonObject2.get("ccerid").equals("") ? -1 : jsonObject2.getInt("ccerid"));
					
					rtn = mapper3.insert(wf4cc);
				}
				
				preNodeId = wn.getId();
				wn = new WorkflowNode();
				wn.setWorkflowid(wf.getId());
				wn.setNodename("已结束");
				wn.setSequence(100);
				wn.setPrelink(preNodeId);
				wn.setNextlink(preNodeId + 2);
				rtn = mapper2.insert(wn);
				if(rtn > 0)
					jSon = "{\"success\": true}";
				
			}
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