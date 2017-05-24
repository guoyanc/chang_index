package com.chang.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chang.util.CommonUtil;

public class FileAction {
	private static Logger logger = LogManager.getLogger(FileAction.class.getName());

	public Iterator<File> getSubDirList(String path) {
		String fileRoot = path;
		File dir = new File(fileRoot);
		Iterator<File> iteFile = FileUtils.iterateFilesAndDirs(dir, FileFileFilter.FILE, DirectoryFileFilter.DIRECTORY);
		return iteFile;
	}
	
	public Map<String, List<File>> getPackage(Iterator<File> iteFile) {
		File file1 = null;
		File file2 = null;
		Map<String, List<File>> fileMap = new HashMap<>();
		List<File> fileList = new ArrayList<>();
		logger.debug(iteFile.next().getAbsolutePath());
		while(iteFile.hasNext()) {
			file1 = iteFile.next();
			logger.debug(file1.getAbsolutePath());
			if(file1.isDirectory()) {
				fileMap.put(file1.getAbsolutePath(), fileList);
				while(iteFile.hasNext()) {
					file2 = iteFile.next();
					logger.debug(file2.getAbsolutePath());
					if(this.isSameDir(file1, file2)){
						logger.debug("true");
						fileList = new ArrayList<>();
						break;
					}
					else {
						fileList.add(file2);
					}
				}
			}
			else {
				fileList.add(file1);
			}
		}
		return fileMap;
	}
	
	public List<File> getDirectoryName(Iterator<File> iteFile) {
		List<File> list = new ArrayList<>();
		File file = null;
		String parentDirectory = iteFile.next().getAbsolutePath();
		while(iteFile.hasNext()) {
			file = iteFile.next();
			if(file.isDirectory() && parentDirectory.equals(file.getParent())) {
				list.add(file);
			}
		}
		return list;
	}
	
	private boolean isSameDir(File file1, File file2) {
		return file1.getParent().equals(file2.getParent());
	}
	
	public String[] getFileName(List<File> list) {
		if(list == null || list.size() == 0) return null;
		String[] fileName = new String[list.size()];
		String tmpName = "";
		for(int i = 0; i < list.size(); i++) {
			tmpName = list.get(i).toString();
			tmpName = tmpName.substring(tmpName.lastIndexOf("\\") + 1);
			if(tmpName.indexOf(']') > 0 ) {
				tmpName = tmpName.substring(tmpName.indexOf(']') + 1);
			}
			fileName[i] = this.resolveFileName(tmpName);
			logger.info("the file name is : " + fileName[i]);
		}
		return fileName;
	}
	
	private String resolveFileName(String fileName) {
		logger.info("the orign file name is : " + fileName);
		if(fileName == null || "".equals(fileName)) return null;
		String str = "";
		String[] strArr = fileName.split("\\.");
		if(strArr.length > 0) {
			for(int i = 0; i < strArr.length; i++) {
				if(!(CommonUtil.isAllNumber(strArr[i]) && strArr[i].length() == 4)) {
					str += strArr[i] + " ";
				}
				else {
					break;
				}
			}
		}
		return str.trim();
	}
}
