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

public class FileAction {

	public Iterator<File> getSubDirList(String path) {
		String fileRoot = path;
		File dir = new File(fileRoot);
		Iterator<File> iteFile = FileUtils.iterateFilesAndDirs(dir, FileFileFilter.FILE, DirectoryFileFilter.DIRECTORY);
		return iteFile;
	}
	
	public Map<String, List> getPackage(Iterator<File> iteFile) {
		File file1 = null;
		File file2 = null;
		Map<String, List> fileMap = new HashMap();
		List<File> fileList = new ArrayList();
		System.out.println(iteFile.next().getAbsolutePath());
		while(iteFile.hasNext()) {
			file1 = iteFile.next();
			System.out.println(file1.getAbsolutePath());
			if(file1.isDirectory()) {
				fileMap.put(file1.getAbsolutePath(), fileList);
				while(iteFile.hasNext()) {
					file2 = iteFile.next();
					System.out.println(file2.getAbsolutePath());
					if(this.isSameDir(file1, file2)){
						System.out.println("true");
						fileList = new ArrayList();
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
		List<File> list = new ArrayList();
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
}
