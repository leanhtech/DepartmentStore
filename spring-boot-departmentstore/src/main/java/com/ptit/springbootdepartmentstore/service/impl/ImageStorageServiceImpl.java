package com.ptit.springbootdepartmentstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ptit.springbootdepartmentstore.common.Constant;
import com.ptit.springbootdepartmentstore.dto.response.FileUploadDto;
import com.ptit.springbootdepartmentstore.entity.Image;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.repository.ImageRepository;
import com.ptit.springbootdepartmentstore.service.FileService;
import com.ptit.springbootdepartmentstore.service.ImageStorageService;
import com.ptit.springbootdepartmentstore.service.ProductService;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	FileService fileService;
	
	Logger logger = LoggerFactory.getLogger(ImageStorageServiceImpl.class);

	@Override
	public Boolean saveProductImage(MultipartFile[] imageFiles, int productId) {
		try {
			List<FileUploadDto> fileName = fileService.uploadFiles(imageFiles);
			Product product = productService.getProductById(productId);
			List<Image> imagesResult = new ArrayList<>();
			for (FileUploadDto file : fileName) {
				Image image = new Image();
				image.setImageUrl(file.getFileUrl());
				image.setStatus(Constant.TRUE_STRING);
				image.setProduct(product);
				imagesResult.add(image);
			}
			imageRepository.saveAll(imagesResult);
		} catch (Exception e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}

}
