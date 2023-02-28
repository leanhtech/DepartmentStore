package com.ptit.springbootdepartmentstore.dto.request;

import lombok.Data;

@Data
public class RequestAddCart {
	
	private Integer productId;
	private Integer userId;

}
