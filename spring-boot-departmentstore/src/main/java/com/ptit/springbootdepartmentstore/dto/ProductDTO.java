package com.ptit.springbootdepartmentstore.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Integer id;

	private String productDescription;

	private String productName;

	private String status;

	private Double price;

	private String specification;

	private String calculationUnit;

	private Integer discount;

	private Integer sold;

	private Integer quantity;
	
	private List<Integer> imageIds;

	private Integer brandId;

	private Integer categoryId;
	
	private String imageBase64;
	
	private List<ProductDTO> recommended;
}