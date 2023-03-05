package com.ptit.springbootdepartmentstore.dto.response;

import java.util.List;

import com.ptit.springbootdepartmentstore.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductListDto {
	
	private String category;
	
	List<ProductDto> products;
}
