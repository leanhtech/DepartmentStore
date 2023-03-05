package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.springbootdepartmentstore.dto.request.RequestOrderList;
import com.ptit.springbootdepartmentstore.dto.response.ResponseOrderList;
import com.ptit.springbootdepartmentstore.entity.Orders;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.service.OrderService;

@RestController
@RequestMapping("/apiorder")
public class OrderController {
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/getallorder")
	public ResponseOrderList getallorder() {
		RequestOrderList requestOrderList = new RequestOrderList();
		return orderService.getOrderList(requestOrderList);
	}
	/*
	 * Not Done
	@PostMapping("/addorder")
	public String AddOrder(@RequestBody Orders order) {
		return orderService.addOrder(order);
	}
	
	@PostMapping("/updateorder")
	public String UpdateOrder (@RequestBody Orders order) {
		return orderService.updateOrder(order);
	}
	*/
	
}
