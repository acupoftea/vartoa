package com.srsoft.service;

import java.io.IOException;

import net.sf.json.JSONObject;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class UploadByQiniu {
	// ���ú��˺ŵ�ACCESS_KEY��SECRET_KEY
	String ACCESS_KEY = "TCZ4q7OROJf39XpZLh1ogRJjz63d7RPHGVe9S4FN";
	String SECRET_KEY = "pUjgTevL6cigNnIEQXho_3PD1POgixEQA9D8GWbZ";
	// Ҫ�ϴ��Ŀռ�
	String bucketname = "vartoa";

	// ��Կ����
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// �����ϴ�����
	UploadManager uploadManager = new UploadManager();

	// ���ϴ���ʹ��Ĭ�ϲ��ԣ�ֻ��Ҫ�����ϴ��Ŀռ����Ϳ�����
	public String getUpToken() {
		return auth.uploadToken(bucketname);
	}

	public String upload(String fileName, String filePath) throws IOException {
		String rtn = "";
		Response res = null;
		
		try {
			// ����put�����ϴ�
			System.out.println("filePath: " + filePath);
			System.out.println("fileName: " + fileName);
			System.out.println("getUpToken: " + getUpToken());
			res = uploadManager.put(filePath, fileName, getUpToken());
			// ��ӡ���ص���Ϣ
			System.out.println("�ϴ�: " + res.bodyString());
			rtn = res.bodyString();
			
		} catch (QiniuException e) {
			Response r = e.response;
			// ����ʧ��ʱ��ӡ���쳣����Ϣ
			System.out.println(r.toString());
			try {
				// ��Ӧ���ı���Ϣ
				System.out.println("�ϴ��쳣: " + r.bodyString());
				rtn = r.bodyString();
			} catch (QiniuException e1) {
				// ignore
			}
		}
		
		return rtn;
	}
}
