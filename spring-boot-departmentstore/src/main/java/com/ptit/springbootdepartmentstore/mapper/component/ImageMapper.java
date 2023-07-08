package com.ptit.springbootdepartmentstore.mapper.component;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.ImageDTO;
import com.ptit.springbootdepartmentstore.entity.Image;
import com.ptit.springbootdepartmentstore.mapper.Mapper;
import com.ptit.springbootdepartmentstore.repository.ImageRepository;

@Component
public class ImageMapper implements Mapper<Image, ImageDTO>{
	
	@Override
	public ImageDTO toDTO (Image image) {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setId(image.getId());
		imageDTO.setImageBase64(ImageRepository.generateImageUrl(image.getImageByte()));
		return imageDTO;
		
	} 
	
	@Override
	public Image toEntity(ImageDTO imageDTO) {
		Image image = new Image();
		image.setId(imageDTO.getId());
		image.setImageByte(ImageRepository.decodeImageUrl(imageDTO.getImageBase64()));
		return image;
		
	}

	@Override
	public List<ImageDTO> toListDTO(List<? extends Image> listEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> toListEntity(List<? extends ImageDTO> listDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
