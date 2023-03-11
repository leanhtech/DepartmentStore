package com.ptit.springbootdepartmentstore.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
	private Integer id;
	
	private String productDescription;
	
	private String productName;
	
	private String status;
	
	private Double price;
	
	private String specification;
	
	private Integer calculationUnit;
	
	private Integer discount;
	
	private Integer sold;
	
	private Integer quantity;
	
	private List<String> image;
}
