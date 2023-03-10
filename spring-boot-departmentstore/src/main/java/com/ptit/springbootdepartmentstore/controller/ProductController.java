package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.springbootdepartmentstore.dto.response.ProductDto;
import com.ptit.springbootdepartmentstore.service.ProductService;


@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	public List<ProductDto> GetAllProduct(){
		return productService.getAllProduct();
	}
	
	
//	@GetMapping("/getimage/{productId}")
//	public List<ProductListDto> GetImage(@PathVariable int productId){
//		return productService.getImageProductByID(productId);
//	}
	
	/*
	 * Not Done
	@GetMapping("/findbyid/{id}")
	public Product FindByName(@PathVariable int id) {
		return productService.findById(id);
	}
	
	@PostMapping("/addproduct")
	public String AddProduct (@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@PostMapping("/addimage")
	public String Addimage (@RequestBody AddImageForm image) {
		return productService.addImage(image);
	}
	
	@PostMapping("/updatecategory")
	public String UpdateCategory (@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	*/
}
