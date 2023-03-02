package com.ptit.springbootdepartmentstore.service;

import org.springframework.web.multipart.MultipartFile;


public interface ImageStorageService {
	
	public String storeFile(MultipartFile file);
	
	public byte[] readFileContent(String fileName);
	
}
