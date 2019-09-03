package com.uiframework.proctur.eLearn.logger.resource;

public class ResourceHelper {
	
	public static String getResourcePath(String path) {
		String basepath = System.getProperty("user.dir");
		System.out.println(basepath + path);
		return basepath + path;
	}
}
