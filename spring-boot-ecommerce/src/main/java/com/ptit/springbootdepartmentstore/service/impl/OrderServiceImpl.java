package com.ptit.springbootdepartmentstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.request.RequestOrderList;
import com.ptit.springbootdepartmentstore.dto.response.ActionStatus;
import com.ptit.springbootdepartmentstore.dto.response.OrderInList;
import com.ptit.springbootdepartmentstore.dto.response.ResponseOrderList;
import com.ptit.springbootdepartmentstore.entity.Orders;
import com.ptit.springbootdepartmentstore.repository.OrderDetailRepository;
import com.ptit.springbootdepartmentstore.repository.OrderRepository;
import com.ptit.springbootdepartmentstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Override
	public OrderInList convertOrder (Orders order) {
		OrderInList orderInList = new OrderInList();
		orderInList.setOrderId(order.getId());
		orderInList.setDate(order.getDate());
		orderInList.setTotalPrice(order.getTotalPrice());
		orderInList.setPaymentMode(order.getPaymentMode());
		orderInList.setStatus(order.getStatus());
		return orderInList;
	}
	
	@Override
	public ResponseOrderList getOrderList (RequestOrderList requestOrderList) {
		ResponseOrderList responseOrderList = new ResponseOrderList();
		List<Orders> orderList = orderRepository.findByUserId(requestOrderList.getUserId());
		if(orderList.isEmpty()) {
			responseOrderList.setStatus(new ActionStatus(false, "Have not Order"));
			return responseOrderList;
		}
		List<OrderInList> orderInList = new ArrayList<>();
		for (Orders order : orderList) {
			orderInList.add(convertOrder(order));
		}
		responseOrderList.setStatus(new ActionStatus(true, "Done"));
		responseOrderList.setOrderInList(orderInList);
		return responseOrderList;
	}
}
