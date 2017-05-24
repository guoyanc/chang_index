package com.chang.util;

import java.util.UUID;

public final class CommonUtil {

	public static final String getUUID() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
	
	public static String getUrl(String content, String startPattern, String endPattern) {
		String url = "";
		int startPos = content.indexOf(startPattern);
		url = content.substring(startPos + 1);
		int endPos = url.indexOf(endPattern);
		return url.substring(0, endPos);
	}
	
	public static boolean isAllNumber(String str) {
		if(str == null || "".equals(str)) return false;
		return str.matches("[0-9]+");
	}
}
