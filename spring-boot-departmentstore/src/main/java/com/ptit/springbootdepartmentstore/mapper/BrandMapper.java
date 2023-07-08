package com.ptit.springbootdepartmentstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.BrandDTO;
import com.ptit.springbootdepartmentstore.entity.Brand;
import com.ptit.springbootdepartmentstore.repository.ImageRepository;

@Component
public class BrandMapper implements Mapper <Brand, BrandDTO>{
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public BrandDTO toDTO(Brand brand) {
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(brand.getId());
		brandDTO.setName(brand.getName());
		brandDTO.setImageBase64(ImageRepository.generateImageUrl(brand.getImageByte()));
		if(brand.getImage() != null)
			brandDTO.setImageId(brand.getImage().getId());
		brandDTO.setDescipttion(brand.getDescipttion());
		return brandDTO;
	}
	
	@Override
	public Brand toEntity(BrandDTO brandDTO) {
		Brand brand = new Brand();
		brand.setId(brandDTO.getId());
		brand.setName(brandDTO.getName());
		brand.setImageByte(ImageRepository.decodeImageUrl(brandDTO.getImageBase64()));
		if(brandDTO.getImageId() != null)
			brand.setImage(imageRepository.findById(brandDTO.getImageId()).orElse(null));
		brand.setDescipttion(brandDTO.getDescipttion());
		return brand;
	}

	@Override
	public List<BrandDTO> toListDTO(List<? extends Brand> listEntity) {
		return listEntity
    			.stream()
    			.map(this::toDTO)
    			.collect(Collectors.toList());
	}

	@Override
	public List<Brand> toListEntity(List<? extends BrandDTO> listDTO) {
		return listDTO
    			.stream()
    			.map(this::toEntity)
    			.collect(Collectors.toList());
	}

}
