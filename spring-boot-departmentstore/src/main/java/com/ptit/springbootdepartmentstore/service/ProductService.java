package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import com.ptit.springbootdepartmentstore.dto.request.ProductRequest;
import com.ptit.springbootdepartmentstore.dto.response.ProductDto;
import com.ptit.springbootdepartmentstore.entity.Brand;
import com.ptit.springbootdepartmentstore.entity.Product;

public interface ProductService {
	
	List<ProductDto> getAllProduct();
	
	Brand getBrandProduct(Product product);
	
	Product addProduct(ProductRequest product);
	
	Product getProductById(int id);
}
