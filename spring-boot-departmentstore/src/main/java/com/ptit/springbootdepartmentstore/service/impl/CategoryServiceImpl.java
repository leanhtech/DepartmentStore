package com.ptit.springbootdepartmentstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.repository.CategoryRepository;
import com.ptit.springbootdepartmentstore.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getCategorys() {
		List<Category> result = categoryRepository.findAll();
		for (Category category : result) {
			List<Product> products = category.getProductList();
			System.out.println(category.getProductList());
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
