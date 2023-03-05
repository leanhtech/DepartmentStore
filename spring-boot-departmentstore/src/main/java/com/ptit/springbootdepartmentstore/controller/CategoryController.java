package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.service.CategoryService;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
	Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/all")
	public List<Category> findAllCategory(){
		return categoryService.getCategorys();
	}
	
	@GetMapping("/{id}")
	public List<Product> AddCategory(@PathVariable("id") int categoryId) {
		return categoryService.getProductsOfCategory(categoryId);
	}
	
	/*
	 * Not Done
	@DeleteMapping("/deletecategory/{id}")
	public String DeleteCaterogy(@PathVariable int id) {
		return categoryService.deleteCategory(id);
	}
	
	@PostMapping("/updatecaregory")
	public String UpdateCaterogy(@RequestBody Category caterogy) {
		return categoryService.updateCategory(caterogy);
	}
	*/
}
