package com.srsoft.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadifyServlet2
 */
public class UploadifyServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadifyServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart)
			return;
		
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8");
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;

		try {
			writer = response.getWriter();
			List<DiskFileItem> items = sfu.parseRequest(request);
			for (DiskFileItem diskFileItem : items) {
				if(!diskFileItem.isFormField()) {
					String tmpFilePath = diskFileItem.getStoreLocation().getAbsolutePath();
					System.out.println("tmpFile:" + tmpFilePath);
					diskFileItem.write(new File(tmpFilePath));
					
					String tmpFileName = URLEncoder.encode(diskFileItem.getName(), "utf-8").toUpperCase();
					System.out.println("�ļ���url����:" + tmpFileName);
					
					UploadByQiniu u = new UploadByQiniu();
					System.out.println("��ʼ�ϴ�");
					//String rtn = u.upload(UUID.randomUUID().toString(), tmpFilePath);
					String rtn = u.upload(tmpFileName, tmpFilePath);
					System.out.println("�ϴ����: " + rtn);
					JSONObject jsonObject = JSONObject.fromObject(rtn);
					if(jsonObject.get("error") == null) {
						DownloadByQiniu d = new DownloadByQiniu();
						
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("url", d.download(URLEncoder.encode(jsonObject.getString("key"), "utf-8")));
						map.put("name", diskFileItem.getName());
						map.put("deleteUrl", "http://developer.qiniu.com/code/v7/sdk/java.html#rs-delete");
						map.put("deleteType", "post");
						
						List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
						list.add(map);
						
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2.put("files", list);
						
						String rtnJson = JSONObject.fromObject(map2).toString();
						System.out.println(rtnJson);
						writer.print(rtnJson);
						
					} else
						writer.print("{\"success\": false, \"msg\": \"�ϴ��ļ�ʧ��\"}");
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String errorInfo = e.getMessage();
			writer.print("{\"success\": false, \"msg\": \"" + errorInfo + "\"}");
			
		} finally {
			writer.flush();
            writer.close();
		}
	}

}
