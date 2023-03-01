package com.ptit.springbootdepartmentstore.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ResponseOrderList {
	
	private ActionStatus status;
	
	private List<OrderInList> orderInList;

}
