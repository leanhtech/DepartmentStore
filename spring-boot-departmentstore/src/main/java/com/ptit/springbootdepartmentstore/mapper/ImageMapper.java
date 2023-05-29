package com.ptit.springbootdepartmentstore.mapper;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.ImageDTO;
import com.ptit.springbootdepartmentstore.entity.Image;

@Component
public class ImageMapper {
	
	public ImageDTO toDTO (Image image) {
		
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setId(image.getId());
		imageDTO.setImageBase64(image.getImageBase64());
		return imageDTO;
		
	} 
	
	public Image toEntity(ImageDTO imageDTO) {
		
		Image image = new Image();
		image.setId(imageDTO.getId());
		image.setImageBase64(imageDTO.getImageBase64());
		return image;
		
	}

}
