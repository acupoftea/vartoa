package com.srsoft.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.srsoft.model.UserDefineFormInstance;
import com.srsoft.util.AutoWriteCode;

public class MyTest {

	@Test
	public void test() {
		AutoWriteCode awc = new AutoWriteCode();
		try {
			awc.write("Resources", true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() throws Exception {
		List<String> warnings = new ArrayList<String>();
		File configFile = new File("src/main/resources/mbg_cfg.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		
		for (String string : warnings) {
			System.out.println(string);
		}
		
		System.out.println("over.");
	}
	
	Object invokeMethod(Object owner, String methodName) throws Exception {  
	     return owner.getClass().getMethod(methodName).invoke(owner);  
	}  
	
	@Test
	public void test3() throws Exception {
		UserDefineFormInstance udfi = new UserDefineFormInstance();
		udfi.setArg0("123");
		udfi.setArg1("456");
		udfi.setArg2("789");
		
		System.out.println(invokeMethod(udfi, "getArg2"));
	}
	
	@Test
	public void test4() throws Exception {
		String str = java.net.URLEncoder.encode("OA功能需求清单.docx", "utf-8");
		System.out.println(str);
		// OA%E5%8A%9F%E8%83%BD%E9%9C%80%E6%B1%82%E6%B8%85%E5%8D%95.docx
	}
	
	@Test
	public void test5() throws Exception {
		// 设置好账号的ACCESS_KEY和SECRET_KEY
		String ACCESS_KEY = "cd49_Oa7kxvx8ebzyqSQmBG8ISYTJTp5Vi6e284t";
		String SECRET_KEY = "3WJoNPdOGHDiFCzV358uPqiBozEo0bKjEel94t8h";
		// 要上传的空间
		String bucketname = "test";

		// 密钥配置
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		
		System.out.println(auth.uploadToken(bucketname));
	}
	
}
