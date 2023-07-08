package com.ptit.springbootdepartmentstore.mapper.component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.OrderDTO;
import com.ptit.springbootdepartmentstore.dto.OrderDetailDTO;
import com.ptit.springbootdepartmentstore.entity.OrderDetail;
import com.ptit.springbootdepartmentstore.entity.OrderDetailId;
import com.ptit.springbootdepartmentstore.entity.Orders;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.mapper.Mapper;
import com.ptit.springbootdepartmentstore.repository.OrderRepository;
import com.ptit.springbootdepartmentstore.repository.ProductRepository;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Component
public class OrderMapper implements Mapper<Orders, OrderDTO>{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public OrderDTO toDTO(Orders order) {
		OrderDTO ordersDTO = new OrderDTO();
		ordersDTO.setId(order.getId());
		ordersDTO.setDate(order.getDate());
		ordersDTO.setStatus(order.getStatus());
		ordersDTO.setTotalPrice(order.getTotalPrice());
		ordersDTO.setPaymentMode(order.getPaymentMode());
		ordersDTO.setUserId(order.getUser().getId());

		List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
		for (OrderDetail orderDetail : order.getOrderDetailList()) {
			OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
			orderDetailDTO.setProductId(orderDetail.getId().getProduct().getId());
			orderDetailDTO.setQuantity(orderDetail.getQuantity());
			orderDetailDTO.setUnitPrice(orderDetail.getUnitPrice());
			orderDetailDTOList.add(orderDetailDTO);
		}
		ordersDTO.setOrderDetailList(orderDetailDTOList);

		return ordersDTO;
	}

	@Override
	public Orders toEntity(OrderDTO ordersDTO) {
		Orders order = new Orders();
		if(ordersDTO.getId() != null)
			order = orderRepository.findById(ordersDTO.getId())
						.orElseThrow(() -> new EntityNotFoundException("Order not found in Orders"));
		else
			order.setId(ordersDTO.getId());
		order.setDate(ordersDTO.getDate());
		order.setStatus(ordersDTO.getStatus());
		order.setTotalPrice(ordersDTO.getTotalPrice());
		order.setPaymentMode(ordersDTO.getPaymentMode());
		User user = userRepository.findById(ordersDTO.getUserId())
						.orElseThrow(() -> new EntityNotFoundException("User not found in Orders"));
		order.setUser(user);

		List<OrderDetail> orderDetailList = new ArrayList<>();
		for (OrderDetailDTO orderDetailDTO : ordersDTO.getOrderDetailList()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setQuantity(orderDetailDTO.getQuantity());
			orderDetail.setUnitPrice(orderDetailDTO.getUnitPrice());
			Product product = productRepository.findById(orderDetailDTO.getProductId())
									.orElseThrow(() -> new EntityNotFoundException("Product not found in Orders"));
			OrderDetailId orderDetailId = new OrderDetailId();
			orderDetailId.setOrder(order);
			orderDetailId.setProduct(product);
			orderDetail.setId(orderDetailId);
			orderDetailList.add(orderDetail);
		}
		order.setOrderDetailList(orderDetailList);

		return order;
	}

	@Override
	public List<OrderDTO> toListDTO(List<? extends Orders> listEntity) {
		return listEntity
    			.stream()
    			.map(this::toDTO)
    			.collect(Collectors.toList());
	}

	@Override
	public List<Orders> toListEntity(List<? extends OrderDTO> listDTO) {
		return listDTO
    			.stream()
    			.map(this::toEntity)
    			.collect(Collectors.toList());
	}
	
}