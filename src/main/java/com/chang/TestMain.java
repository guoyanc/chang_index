package com.chang;

import java.io.File;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chang.file.FileAction;
import com.chang.util.ConfigInfo;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"resource/service.xml");
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
		List<File> list = fileAction.getDirectoryName(fileAction.getSubDirList(configInfo.getCommonFileRoot()));
		for(int i=0; i<list.size(); i++) {
			System.out.println("the index " + i + " + is : " + list.get(i));
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
