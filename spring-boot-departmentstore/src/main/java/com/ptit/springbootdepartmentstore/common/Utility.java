package com.ptit.springbootdepartmentstore.common;

public class Utility {
	
//	private static final String DEFAULT_URL = "http://192.168.2.29:8080/api/image/";

	private static final String DEFAULT_URL = "http://localhost:8080/spring-boot-ecommerce/api/image/";
	
	public static String convertToImageUrl(String imageName) {
		return DEFAULT_URL + imageName;
	}
}
