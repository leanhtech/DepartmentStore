package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import com.ptit.springbootdepartmentstore.dto.response.ProductListDto;
import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.entity.Product;

public interface CategoryService {
	
	List<Category> getCategory();
	
	List<Product> getProductsOfCategory(Integer categoryId);
	
	Category getCategoryByProductId(Integer productId);
	
	List<ProductListDto> getCategoryWithProduct();
	
}
