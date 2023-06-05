package com.ptit.springbootdepartmentstore.dto;

import java.util.List;

import lombok.Data;

@Data
public class DashBoardDTO {
	
	List<OrderDTO> recentOrder;
	
	List<OrderDTO> completedOrder;
	
	List<ProductDTO> lowStockProduct;

}
