package com.ptit.springbootdepartmentstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.request.RequestAddCart;
import com.ptit.springbootdepartmentstore.dto.request.RequestGetCart;
import com.ptit.springbootdepartmentstore.dto.response.ActionStatus;
import com.ptit.springbootdepartmentstore.dto.response.ProductInCart;
import com.ptit.springbootdepartmentstore.dto.response.ResponseGetCart;
import com.ptit.springbootdepartmentstore.entity.Cart;
import com.ptit.springbootdepartmentstore.entity.CartId;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.repository.CartRepository;
import com.ptit.springbootdepartmentstore.repository.ProductRepository;
import com.ptit.springbootdepartmentstore.repository.UserRepository;
import com.ptit.springbootdepartmentstore.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	private ActionStatus status;
	
	@Override
	public ActionStatus addProductToCart (RequestAddCart addCart) {
		User user = userRepository.getOne(addCart.getUserId());
		Product product = productRepository.getOne(addCart.getProductId());
		CartId cartId = new CartId(user, product);
		Cart cart = new Cart();
		Boolean exist = cartRepository.existsById(cartId);
		if (exist == false) {
			cart.setId(cartId);
			cart.setQuantity(1);
			cartRepository.save(cart);
		}
		cart = cartRepository.getOne(cartId);
		cart.setQuantity(cart.getQuantity()+1);
		cartRepository.save(cart);
		return new ActionStatus(true, "Add product to cart done");
	}
	
	@Override
	public ProductInCart convertProduct (Product product, Integer quantity) {
		ProductInCart productInCart = new ProductInCart();
		productInCart.setProductId(product.getId());
		productInCart.setProductName(product.getProductName());
		productInCart.setPrice(product.getPrice());
		productInCart.setShortDescription(product.getProductDescription());
		productInCart.setQuantity(quantity);
		return productInCart;
	}
	
	@Override
	public ResponseGetCart getCart (RequestGetCart getCart) {
		List<Cart> listCart = new ArrayList<>();
		ResponseGetCart responseCart = new ResponseGetCart();
		List<ProductInCart> listProductInCart = new ArrayList<>();
		listCart = cartRepository.findByIdUserId(getCart.getUserId());
		if(listCart.isEmpty()) {
			status.setStatus(false);
			status.setMessenge("No Product In Cart");
			responseCart.setStatus(status);
		}
		status.setStatus(true);
		for (Cart cart : listCart) {
			listProductInCart.add(convertProduct(cart.getId().getProduct(),cart.getQuantity()));
		}
		responseCart.setProductList(listProductInCart);
		return responseCart;
	}

}
