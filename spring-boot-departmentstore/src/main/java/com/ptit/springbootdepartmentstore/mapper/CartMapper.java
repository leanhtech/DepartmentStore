package com.ptit.springbootdepartmentstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.CartDTO;
import com.ptit.springbootdepartmentstore.entity.Cart;
import com.ptit.springbootdepartmentstore.entity.CartId;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.entity.User;

@Component
public class CartMapper implements Mapper<Cart, CartDTO>{
	
	@Autowired
	private ProductMapper productMapper;

	@Override
    public CartDTO toDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(cart.getId().getUser().getId());
        cartDTO.setProduct(productMapper.toDTO(cart.getId().getProduct()));
        cartDTO.setQuantity(cart.getQuantity());
        return cartDTO;
    }

    public Cart toEntity(CartDTO cartDTO, User user, Product product) {
        Cart cart = new Cart();
        CartId cartId = new CartId();
        cartId.setUser(user);
        cartId.setProduct(product);
        cart.setId(cartId);
        cart.setQuantity(cartDTO.getQuantity());
        return cart;
    }

	@Override
	public Cart toEntity(CartDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartDTO> toListDTO(List<? extends Cart> listEntity) {
		return listEntity
    			.stream()
    			.map(this::toDTO)
    			.collect(Collectors.toList());
	}

	@Override
	public List<Cart> toListEntity(List<? extends CartDTO> listDTO) {
		return listDTO
    			.stream()
    			.map(this::toEntity)
    			.collect(Collectors.toList());
	}

}
