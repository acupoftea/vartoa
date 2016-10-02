package com.srsoft.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UploadifyServlet3
 */
public class UploadifyServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadifyServlet3() {
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
		PrintWriter writer = response.getWriter();

		String dispoString = request.getHeader("Content-Disposition");  
        int iFindStart = dispoString.indexOf("name=\"") + 6;  
        int iFindEnd = dispoString.indexOf("\"", iFindStart);  
        iFindStart = dispoString.indexOf("filename=\"") + 10;  
        iFindEnd = dispoString.indexOf("\"", iFindStart);  
        String sFileName = dispoString.substring(iFindStart, iFindEnd);  
        int i = request.getContentLength();  
        byte buffer[] = new byte[i];  
        
        String tmpdir = System.getProperty("java.io.tmpdir");
        //String fileName = tmpdir + UUID.randomUUID();
        String fileName = tmpdir + sFileName;
        
        BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName));
        int readLen = 0;
        do {
	        readLen = bis.read(buffer);
	        if(readLen > 0)
	        	bos.write(buffer, 0, readLen);
	        
        } while(readLen > 0);
        bos.close();
        bis.close();
        
		UploadByQiniu u = new UploadByQiniu();
		System.out.println("开始上传");
		String rtn = u.upload(sFileName, fileName);
		System.out.println("上传完成: " + rtn);
		JSONObject jsonObject = JSONObject.fromObject(rtn);
		if(jsonObject.get("error") == null) {
			DownloadByQiniu d = new DownloadByQiniu();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("err", "");
			map.put("msg", d.download(URLEncoder.encode(jsonObject.getString("key"), "utf-8")));
			
			writer.print(JSONObject.fromObject(map).toString());
			
		} else
			writer.print("{\"err\": \"上传异常\", \"msg\": \"上传文件失败\"}");
        
	}

}
