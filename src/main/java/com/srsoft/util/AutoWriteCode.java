package com.srsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class AutoWriteCode {
	void updateMybatisConfiguration(String tableName) throws Exception {
		SAXReader saxReader = new SAXReader();
		File xmlFile = new File("src/main/resources/mybatis_configuration.xml");
		Document document = saxReader.read(xmlFile);
		
		List list = document.selectNodes("configuration/mappers");
		if(list.size() > 0) {
			Element element = (Element) list.get(0);
			//tableName = tableName.replaceFirst(tableName.substring(0, 1),tableName.substring(0, 1).toLowerCase());
			DOMElement newMapper = new DOMElement("mapper");
			newMapper.addAttribute("resource", "com/srsoft/mappers/" + tableName + "Mapper.xml");
			element.add(newMapper);
		}
		
		OutputFormat of = OutputFormat.createPrettyPrint();
		of.setTrimText(false);
		XMLWriter writer = new XMLWriter(new FileWriter(xmlFile), of);
        writer.write(document);
        writer.setEscapeText(false);
        writer.close();
        
        System.out.println("更新mybatis_configuration.xml成功");
	}
	
	void callMbg(String tableName) throws Exception {
		SAXReader saxReader = new SAXReader();
		File xmlFile = new File("src/main/resources/mbg_cfg.xml");
		Document document = saxReader.read(xmlFile);
		
		List list = document.selectNodes("generatorConfiguration/context/table");
		if(list.size() > 0) {
			Element element = (Element) list.get(0);
			element.addAttribute("domainObjectName", tableName);
			
			tableName = tableName.replaceFirst(tableName.substring(0, 1),tableName.substring(0, 1).toLowerCase());
			element.addAttribute("tableName", tableName);
		}
		
		OutputFormat of = OutputFormat.createPrettyPrint();
		of.setTrimText(false);
		XMLWriter writer = new XMLWriter(new FileWriter(xmlFile), of);
        writer.write(document);
        writer.setEscapeText(false);
        writer.close();
        
        System.out.println("更新mbg_cfg.xml成功");
	}
	
	public void write(String tableName, boolean isCallMbg) {
		if(isCallMbg)
			try {
				callMbg(tableName);
				
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
				
				System.out.println("生成dao; mappers; model成功");
				
				updateMybatisConfiguration(tableName);
				
				updateSpringConfig(tableName);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}
		
		File templateFile = new File("src/main/java/com/srsoft/util/codetemplate/xxxService.txt");
		String destFileName = "src/main/java/com/srsoft/service/" + tableName + "Service.java";
		
		try {
			FileInputStream  fis = new FileInputStream(templateFile);
			//FileOutputStream fos = new FileOutputStream(destFile);
			
			byte[] buf = new byte[1024];
	        StringBuffer sb = new StringBuffer();
	        while((fis.read(buf))!=-1) {
	        	sb.append(new String(buf));    
	            buf = new byte[1024];//重新生成，避免和上次读取的数据重复
	        }
	        
	        fis.close();
	        
	        String fileContent = sb.toString();
	        fileContent = fileContent.replace("[#tableName$]", tableName);
	        
	        FileWriter writer = new FileWriter(destFileName);
            writer.write(fileContent);
            writer.close();
            
            System.out.println(destFileName);
            System.out.println("生成成功");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateSpringConfig(String tableName) throws Exception {
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		File xmlFile = new File("src/main/resources/spring_base.xml");
		Document document = saxReader.read(xmlFile);
		
		List list = document.selectNodes("beans");
		if(list.size() > 0) {
			Element element = (Element) list.get(0);
			DOMElement newElement = new DOMElement("bean");
			
			String tableNameLower = tableName.replaceFirst(tableName.substring(0, 1),tableName.substring(0, 1).toLowerCase());
			newElement.addAttribute("id", tableNameLower + "Service");
			newElement.addAttribute("class", "com.srsoft.service." + tableName + "Service");
			element.add(newElement);
		}
		
		OutputFormat of = OutputFormat.createPrettyPrint();
		of.setTrimText(false);
		XMLWriter writer = new XMLWriter(new FileWriter(xmlFile), of);
        writer.write(document);
        writer.setEscapeText(false);
        writer.close();
        
        System.out.println("更新spring_base.xml成功");
        
        xmlFile = new File("src/main/resources/spring_main.xml");
		document = saxReader.read(xmlFile);
		
		list = document.selectNodes("beans/bean/property/set");
		if(list.size() > 0) {
			Element element = (Element) list.get(0);
			DOMElement newElement = new DOMElement("ref");
			
			String tableNameLower = tableName.replaceFirst(tableName.substring(0, 1),tableName.substring(0, 1).toLowerCase());
			newElement.addAttribute("bean", tableNameLower + "Service");
			element.add(newElement);
		}
		
		of = OutputFormat.createPrettyPrint();
		of.setTrimText(false);
		writer = new XMLWriter(new FileWriter(xmlFile), of);
        writer.write(document);
        writer.setEscapeText(false);
        writer.close();
        
        System.out.println("更新spring_main.xml成功");
	}
}
