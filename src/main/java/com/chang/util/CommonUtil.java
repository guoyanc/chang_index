package com.chang.util;

import java.util.UUID;

public final class CommonUtil {

	public static final String getUUID() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
}
