package com.ptit.springbootdepartmentstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.OrderDTO;
import com.ptit.springbootdepartmentstore.entity.OrderDetail;
import com.ptit.springbootdepartmentstore.entity.Orders;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.mapper.BaseMapperFactory;
import com.ptit.springbootdepartmentstore.mapper.ConstantMapper;
import com.ptit.springbootdepartmentstore.mapper.Mapper;
import com.ptit.springbootdepartmentstore.mapper.MapperFactory;
import com.ptit.springbootdepartmentstore.mapper.component.OrderMapper;
import com.ptit.springbootdepartmentstore.repository.OrderRepository;
import com.ptit.springbootdepartmentstore.repository.ProductRepository;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

//	@Autowired
//	private OrderMapper orderMapper;
	
	@Autowired
	private MailService mailService;
	
    private BaseMapperFactory mapperFactory = new MapperFactory();

	private Mapper orderMapper = mapperFactory.Choose(ConstantMapper.ORDER);

	public List<OrderDTO> getAllOrders() {
		List<Orders> orders = orderRepository.findAll();
		List<OrderDTO> ordersDTOList = new ArrayList<>();

		for (Orders order : orders) {
			OrderDTO ordersDTO = (OrderDTO) orderMapper.toDTO(order);
			ordersDTOList.add(ordersDTO);
		}

		return ordersDTOList;
	}
	
	public List<OrderDTO> getAllOrdersByUserId(Integer userId) {
		List<Orders> orders = orderRepository.findByUserId(userId);
		
		List<OrderDTO> ordersDTOList = new ArrayList<>();

		for (Orders order : orders) {
			OrderDTO ordersDTO = (OrderDTO) orderMapper.toDTO(order);
			ordersDTOList.add(ordersDTO);
		}

		return ordersDTOList;
	}

	public OrderDTO getOrderById(Integer id) {
		Orders order = orderRepository.findById(id).orElse(null);
		if (order != null) {
			OrderDTO ordersDTO = (OrderDTO) orderMapper.toDTO(order);
			return ordersDTO;
		} else {
			return null;
		}
	}

	public OrderDTO addOrder(OrderDTO ordersDTO) {
		Orders order = (Orders) orderMapper.toEntity(ordersDTO);
		User user = userRepository.findById(ordersDTO.getUserId()).orElse(null);
		if (user != null) {
			order.setUser(user);
		} else {
			return null;
		}

		List<OrderDetail> orderDetailList = order.getOrderDetailList();
		for (OrderDetail orderDetail : orderDetailList) {
			Product product = productRepository.findById(orderDetail.getId().getProduct().getId()).orElse(null);
			if (product != null) {
				orderDetail.getId().setProduct(product);
			} else {
				return null;
			}
		}

		Orders savedOrder = orderRepository.save(order);
		if(savedOrder.getUser().getEmail() != null)
			mailService.sendSimpleEmail(
					savedOrder.getUser().getEmail(), 
					"New Order in Online Shop", 
					"Username : " + savedOrder.getUser().getName() + 
					" And ID Order : " + String.valueOf(savedOrder.getId()) +
					" And Status : " + savedOrder.getStatus());
		OrderDTO savedOrderDTO = (OrderDTO) orderMapper.toDTO(savedOrder);
		return savedOrderDTO;
	}

	public OrderDTO updateOrder(Integer id, OrderDTO ordersDTO) {
		Orders order = orderRepository.findById(id).orElse(null);
		if (order != null) {
			order = (Orders) orderMapper.toEntity(ordersDTO);
			order.setId(id);
			User user = userRepository.findById(ordersDTO.getUserId()).orElse(null);
			if (user != null) {
				order.setUser(user);
			} else {
				return null;
			}

			List<OrderDetail> orderDetailList = order.getOrderDetailList();
			for (OrderDetail orderDetail : orderDetailList) {
				Product product = productRepository.findById(orderDetail.getId().getProduct().getId()).orElse(null);
				if (product != null) {
					orderDetail.getId().setProduct(product);
				} else {
					return null;
				}
			}

			Orders savedOrder = orderRepository.save(order);
			if(savedOrder.getUser().getEmail() != null)
				mailService.sendSimpleEmail(
						savedOrder.getUser().getEmail(), 
						"Update Order in Online Shop", 
						"Username : " + savedOrder.getUser().getName() + 
						" And ID Order : " + String.valueOf(savedOrder.getId()) +
						" And Status : " + savedOrder.getStatus());
			OrderDTO savedOrderDTO = (OrderDTO) orderMapper.toDTO(savedOrder);
			return savedOrderDTO;
		} else {
			return null;
		}
	}

	public boolean deleteOrder(Integer id) {
		Orders order = orderRepository.findById(id).orElse(null);
		if (order != null) {
			orderRepository.delete(order);
			return true;
		} else {
			return false;
		}
	}
}
