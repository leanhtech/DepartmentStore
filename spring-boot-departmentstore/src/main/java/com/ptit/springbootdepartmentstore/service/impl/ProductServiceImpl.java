package com.ptit.springbootdepartmentstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.response.ProductDto;
import com.ptit.springbootdepartmentstore.entity.Brand;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.repository.ProductRepository;
import com.ptit.springbootdepartmentstore.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public List<ProductDto> getAllProduct() {
		List<Product> products = productRepository.findAll();
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
			product.setQuantity(product.getQuantity());
			productDto.setCategory(product.getCategory());
//			product.setBrand(product.getBrand());
			result.add(productDto);
		}
		return result;
	}

	@Override
	public Brand getBrandProduct(Product product) {
		return product.getBrand();
	}
	
}
