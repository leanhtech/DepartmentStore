package com.ptit.springbootdepartmentstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.common.Utility;
import com.ptit.springbootdepartmentstore.dto.response.ProductDto;
import com.ptit.springbootdepartmentstore.dto.response.ProductListDto;
import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.entity.Image;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.repository.CategoryRepository;
import com.ptit.springbootdepartmentstore.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getCategory() {
		List<Category> result = categoryRepository.findAll();
		return result;
	}

	@Override
	public Category getCategoryByProductId(Integer productId) {
		return categoryRepository.findById(productId).orElse(null);
	}

	@Override
	public List<ProductListDto> getCategoryWithProduct() {
		List<Category> categories = categoryRepository.findAll();
		List<ProductListDto> result = new ArrayList<>();
		ProductListDto dto = null;
		for (Category category : categories) {
			dto = new ProductListDto();
			dto.setCategory(category.getCategoryName());
			List<Product> products = getProductsOfCategory(category.getId());
			List<ProductDto> productDto = convertToDto(products);
			dto.setProducts(productDto);
			result.add(dto);
		}
		return result;
	}
	
	public List<ProductDto> convertToDto(List<Product> products) {
		List<ProductDto> result = new ArrayList<>();
		for (Product product : products) {
			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setProductDescription(product.getProductDescription());
			productDto.setProductName(product.getProductName());
			productDto.setStatus(product.getStatus());
			productDto.setPrice(product.getPrice());
			productDto.setSpecification(product.getSpecification());
			productDto.setCalculationUnit(product.getCalculationUnit());
			productDto.setDiscount(product.getDiscount());
			productDto.setSold(product.getSold());
			productDto.setQuantity(product.getQuantity());
			List<Image> images = product.getImageList();
			List<String> imgResult = new ArrayList<>();
			for (Image img : images) {
				String imgConverted = Utility.convertToImageUrl(img.getImageUrl());
				imgResult.add(imgConverted);
			}
			productDto.setImage(imgResult);
			result.add(productDto);
		}
		return result;
	}

	@Override
	public List<Product> getProductsOfCategory(Integer categoryId) {
		Category category =  categoryRepository.findById(categoryId).orElse(null);
		List<Product> products = new ArrayList<>();
		if (category != null) {
			products = category.getProductList();
		}
		return products;
	}

}
