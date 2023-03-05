package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.request.RequestOrderList;
import com.ptit.springbootdepartmentstore.dto.response.OrderInList;
import com.ptit.springbootdepartmentstore.dto.response.ResponseOrderList;
import com.ptit.springbootdepartmentstore.entity.Orders;

@Service
public interface OrderService {
	
	public OrderInList convertOrder (Orders order);
	
	public ResponseOrderList getOrderList (RequestOrderList requestOrderList);
}
