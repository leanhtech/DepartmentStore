package com.ptit.springbootdepartmentstore.mapper;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.CategoryDTO;
import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.repository.ImageRepository;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDTO>{
	
//	private String generateAvatarUrl(byte[] avatar) {
//		if (avatar == null) {
//			return null;
//		}
//		return "data:image/png;base64," + Base64.getEncoder().encodeToString(avatar);
//	}
	
	@Override
	public CategoryDTO toDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setNote(category.getNote());
		if(category.getImageByte() != null)
			categoryDTO.setImageBase64(ImageRepository.generateImageUrl(category.getImageByte()));
//		if(category.getImage() != null)
//			categoryDTO.setImageUrl(generateAvatarUrl(category.getImage()));
		return categoryDTO;
	} 
	
	@Override
	public Category toEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setNote(categoryDTO.getNote());
		if(categoryDTO.getImageBase64() != null)
			category.setImageByte(ImageRepository.decodeImageUrl(categoryDTO.getImageBase64()));
		return category;
	}

	@Override
	public List<CategoryDTO> toListDTO(List<? extends Category> listEntity) {
		return listEntity
    			.stream()
    			.map(this::toDTO)
    			.collect(Collectors.toList());
	}

	@Override
	public List<Category> toListEntity(List<? extends CategoryDTO> listDTO) {
		return listDTO
    			.stream()
    			.map(this::toEntity)
    			.collect(Collectors.toList());
	}

}
