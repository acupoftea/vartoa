package com.srsoft.service;

import com.qiniu.util.Auth;

public class DownloadByQiniu {
	// ���ú��˺ŵ�ACCESS_KEY��SECRET_KEY
	String ACCESS_KEY = "TCZ4q7OROJf39XpZLh1ogRJjz63d7RPHGVe9S4FN";
	String SECRET_KEY = "pUjgTevL6cigNnIEQXho_3PD1POgixEQA9D8GWbZ";
	// ��Կ����
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// ����˽�пռ���Ҫ���ɵ����ص�����
	//String URL = "http://o8m5a57vc.bkt.clouddn.com/";
	String URL = "http://o8l8w1sjg.bkt.clouddn.com/";

	public String download(String key) {
		// ����privateDownloadUrl����������������,�ڶ���������������Token�Ĺ���ʱ��
		String downloadRUL = auth.privateDownloadUrl(URL + key);
		System.out.println("��������: " + downloadRUL);
		return downloadRUL;
	}
}
