package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.entity.Product;

public interface CategoryService {
	
	List<Category> getCategorys();
	
	List<Product> getProductsOfCategory(Integer categoryId);
}
