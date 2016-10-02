package com.srsoft.service;

import com.qiniu.util.Auth;

public class DownloadByQiniu {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "TCZ4q7OROJf39XpZLh1ogRJjz63d7RPHGVe9S4FN";
	String SECRET_KEY = "pUjgTevL6cigNnIEQXho_3PD1POgixEQA9D8GWbZ";
	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 构造私有空间需要生成的下载的链接
	//String URL = "http://o8m5a57vc.bkt.clouddn.com/";
	String URL = "http://o8l8w1sjg.bkt.clouddn.com/";

	public String download(String key) {
		// 调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
		String downloadRUL = auth.privateDownloadUrl(URL + key);
		System.out.println("下载链接: " + downloadRUL);
		return downloadRUL;
	}
}
