package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.springbootdepartmentstore.entity.Category;


@RestController
@RequestMapping("/apicategory")
public class CategoryController {
	Logger logger = LoggerFactory.getLogger(CategoryController.class);
	/*
	 * Not Done
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/getallcategory")
	public List<Category> findAllCategory(){
		return categoryService.getCategorys();
	}
	
	@PostMapping("/addcategory")
	public String AddCategory(@RequestBody Category caterogy) {
		return categoryService.addCatagory(caterogy);
	}
	
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
