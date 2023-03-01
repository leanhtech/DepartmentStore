package com.ptit.springbootdepartmentstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/apiproduct")
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	/*
	 * Not Done
	@Autowired
	private ProductService productService;
	
	@GetMapping("/getallproduct")
	public List<Product> GetAllProduct(){
		return productService.getAllProduct();
	}
	
	@GetMapping("/getimage/{productId}")
	public List<Image> GetImage(@PathVariable int productId){
		return productService.getImageProductByID(productId);
	}
	
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
