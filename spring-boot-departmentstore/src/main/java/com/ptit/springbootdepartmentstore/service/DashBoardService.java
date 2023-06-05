package com.ptit.springbootdepartmentstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.DashBoardDTO;
import com.ptit.springbootdepartmentstore.mapper.OrderMapper;
import com.ptit.springbootdepartmentstore.mapper.ProductMapper;
import com.ptit.springbootdepartmentstore.repository.OrderRepository;
import com.ptit.springbootdepartmentstore.repository.ProductRepository;

@Service
public class DashBoardService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	public DashBoardDTO getDashBoard() {
		DashBoardDTO boardDTO = new DashBoardDTO();
		boardDTO.setRecentOrder(orderMapper.toListDTO(orderRepository.findTop3ByOrderByDateDesc()));
		boardDTO.setCompletedOrder(orderMapper.toListDTO(orderRepository.findTop3ByStatusOrderByDateDesc("Đã giao")));
		boardDTO.setLowStockProduct(productMapper.toListDTO(productRepository.findTop3ByOrderByQuantity()));
		return boardDTO;
	}

}
