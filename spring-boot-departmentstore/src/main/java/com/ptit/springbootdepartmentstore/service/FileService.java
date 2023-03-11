package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ptit.springbootdepartmentstore.dto.response.FileUploadDto;

public interface FileService {
	
	List<FileUploadDto> uploadFiles(MultipartFile[] files);
	
}
