package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ptit.springbootdepartmentstore.dto.response.FileUploadDto;
import com.ptit.springbootdepartmentstore.service.FileService;
import com.ptit.springbootdepartmentstore.service.ImageStorageService;

@RestController
@RequestMapping("/api/image")
public class ImageController {

	@Autowired
	ImageStorageService imageStorageService;

	@Autowired
	FileService fileService;

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<List<FileUploadDto>> addFile(@RequestParam("files") MultipartFile[] files) {
		final List<FileUploadDto> result = fileService.uploadFiles(files);
		return new ResponseEntity<List<FileUploadDto>>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/product/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Boolean> uploadImageProduct(@RequestParam("files") MultipartFile[] file, @PathVariable int id) {
		final Boolean result = imageStorageService.saveProductImage(file, id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
