package com.ptit.springbootdepartmentstore.dto.response;

import java.sql.Date;

import lombok.Data;

@Data
public class OrderInList {
	
	private Integer orderId;
	private Date date;
	private String status;
	private Integer totalPrice;
	private String paymentMode;

}
