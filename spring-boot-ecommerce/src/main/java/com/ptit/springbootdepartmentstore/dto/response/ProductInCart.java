package com.ptit.springbootdepartmentstore.dto.response;

import lombok.Data;

@Data
public class ProductInCart {
	
	private Integer productId;
	
	private String productName;
	
	private String shortDescription;
	
	private Double price;
	
	private Integer quantity;

}
