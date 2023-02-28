package com.ptit.springbootdepartmentstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionStatus {
	
	private boolean status;
	private String messenge;

}
