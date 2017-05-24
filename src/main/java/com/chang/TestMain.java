package com.chang;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chang.action.HttpAction;
import com.chang.file.FileAction;
import com.chang.util.ConfigInfo;

public class TestMain {

	/**
	 * @param args
	 */

	private static Logger logger = LogManager.getLogger(TestMain.class.getName());
	
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"resource/service.xml");
//		PropertyConfigurator.configure("./resource/log4j.properties");
//		BasicDataSource ds = (BasicDataSource) context.getBean(BasicDataSource.class);
//		ConfigInfo configInfo = (ConfigInfo) context.getBean(ConfigInfo.class);
//		System.out.println("the file root is : " + configInfo.getCommonFileRoot());
//		try {
//			Connection conn = ds.getConnection();
//			System.out.println(conn.toString());
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		FileAction fileAction = (FileAction)context.getBean(FileAction.class);
		ConfigInfo configInfo = (ConfigInfo)context.getBean(ConfigInfo.class);
		HttpAction httpAction = (HttpAction)context.getBean(HttpAction.class);
		List<File> list = fileAction.getDirectoryName(fileAction.getSubDirList(configInfo.getCommonFileRoot()));
		String fileName[] = fileAction.getFileName(list);
		for(int i = 0; i < fileName.length; i++) {
			httpAction.getMediaSearchURL("https://www.douban.com/search?source=suggest&q=" + fileName[i].replace(' ', '.'));
		}
//		System.out.println(list);
		
//		System.out.println("C:/chang/temp/media/Resident.Evil.The.Final.Chapter.2016.720p.BluRay.X264-AMIABLE".toLowerCase().indexOf("720pp".toLowerCase()));
//		Iterator<File> iteFile = fileAction.getSubDirList(configInfo.getCommonFileRoot());
//		while(iteFile.hasNext()) {
//			File file = iteFile.next();
//			System.out.println("the path is : " + file.getAbsolutePath());
//		}
//		String[] str = configInfo.getCommonFileMediaSign();
//		for(int i=0; i<str.length; i++) {
//			System.out.println("str[" + i + "] = " + str[i]);
//		}
	}
	
}
