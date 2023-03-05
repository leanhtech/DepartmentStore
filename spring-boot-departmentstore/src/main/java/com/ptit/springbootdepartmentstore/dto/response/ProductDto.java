package com.ptit.springbootdepartmentstore.dto.response;

import com.ptit.springbootdepartmentstore.entity.Brand;
import com.ptit.springbootdepartmentstore.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

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
	
	private String image;
	
	private Brand brand;
	
	private Category category;
}
