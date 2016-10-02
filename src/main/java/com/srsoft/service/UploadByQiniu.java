package com.srsoft.service;

import java.io.IOException;

import net.sf.json.JSONObject;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class UploadByQiniu {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "TCZ4q7OROJf39XpZLh1ogRJjz63d7RPHGVe9S4FN";
	String SECRET_KEY = "pUjgTevL6cigNnIEQXho_3PD1POgixEQA9D8GWbZ";
	// 要上传的空间
	String bucketname = "vartoa";

	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 创建上传对象
	UploadManager uploadManager = new UploadManager();

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
		return auth.uploadToken(bucketname);
	}

	public String upload(String fileName, String filePath) throws IOException {
		String rtn = "";
		Response res = null;
		
		try {
			// 调用put方法上传
			System.out.println("filePath: " + filePath);
			System.out.println("fileName: " + fileName);
			System.out.println("getUpToken: " + getUpToken());
			res = uploadManager.put(filePath, fileName, getUpToken());
			// 打印返回的信息
			System.out.println("上传: " + res.bodyString());
			rtn = res.bodyString();
			
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println("上传异常: " + r.bodyString());
				rtn = r.bodyString();
			} catch (QiniuException e1) {
				// ignore
			}
		}
		
		return rtn;
	}
}
