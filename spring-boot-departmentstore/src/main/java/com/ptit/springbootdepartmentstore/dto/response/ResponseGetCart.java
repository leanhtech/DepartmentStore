package com.ptit.springbootdepartmentstore.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ResponseGetCart {
	
	private ActionStatus status;
	
	private List<ProductInCart> productList;

}
