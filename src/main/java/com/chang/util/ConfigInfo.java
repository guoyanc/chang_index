package com.chang.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("configInfo")
public class ConfigInfo {

	@Value("${common.file.root}")
	private String commonFileRoot;
	
	@Value("${common.file.media.sign}")
	private String commonFileMediaSign;
	
	public String getCommonFileRoot() {
		return commonFileRoot;
	}
	
	public String[] getCommonFileMediaSign() {
		String[] str = StringUtils.split(commonFileMediaSign, '|');
		return str;
	}

}
