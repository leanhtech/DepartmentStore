package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.springbootdepartmentstore.dto.CartDTO;
import com.ptit.springbootdepartmentstore.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	@Autowired
	private CartService cartService;

	@GetMapping("/")
	public List<CartDTO> getAllCarts() {
		return cartService.getAllCarts();
	}
	
	@GetMapping("/{userId}")
	public List<CartDTO> getAllByUser(@PathVariable Integer userId) {
		return cartService.getAllByIdUser(userId);
	}

	@GetMapping("/{userId}/{productId}")
	public CartDTO getCartByUserIdAndProductId(@PathVariable Integer userId, @PathVariable Integer productId) {
		return cartService.getCartByUserIdAndProductId(userId, productId);
	}

	@PostMapping("/{userId}/{productId}/{quatily}")
	public List<CartDTO> addToCart(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer quatily) {
		return cartService.addToCart(userId, productId, quatily);
	}

	@PutMapping("/{userId}/{productId}")
	public List<CartDTO> updateCart(@PathVariable Integer userId, @PathVariable Integer productId,
			@RequestBody CartDTO cartDTO) {
		return cartService.updateCart(userId, productId, cartDTO.getQuantity());
	}

	@DeleteMapping("/{userId}/{productId}")
	public void deleteCart(@PathVariable Integer userId, @PathVariable Integer productId) {
		cartService.deleteCart(userId, productId);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteCartByUser(@PathVariable Integer userId) {
		cartService.deleteCartByUserId(userId);
	}

}
