package com.ptit.springbootdepartmentstore.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
	
	public Boolean saveProductImage(MultipartFile[] imageName, int productId);
	
}
