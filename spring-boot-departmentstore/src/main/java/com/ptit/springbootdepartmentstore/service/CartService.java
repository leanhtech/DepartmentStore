package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.CartDTO;
import com.ptit.springbootdepartmentstore.entity.Cart;
import com.ptit.springbootdepartmentstore.entity.CartId;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.mapper.BaseMapperFactory;
import com.ptit.springbootdepartmentstore.mapper.ConstantMapper;
import com.ptit.springbootdepartmentstore.mapper.Mapper;
import com.ptit.springbootdepartmentstore.mapper.MapperFactory;
import com.ptit.springbootdepartmentstore.mapper.component.CartMapper;
import com.ptit.springbootdepartmentstore.mapper.component.ProductMapper;
import com.ptit.springbootdepartmentstore.repository.CartRepository;
import com.ptit.springbootdepartmentstore.repository.ProductRepository;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartMapper mapper;
    
    @Autowired
	private ProductMapper productMapper;
    
    private BaseMapperFactory mapperFactory = new MapperFactory();

	private Mapper cartMapper = mapperFactory.Choose(ConstantMapper.CART);

    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return cartMapper.toListDTO(carts);
    }
    
    public List<CartDTO> getAllByIdUser(Integer id) {
    	List<Cart> carts = cartRepository.findByIdUserId(id);
    	return cartMapper.toListDTO(carts);
    }

    public CartDTO getCartByUserIdAndProductId(Integer userId, Integer productId) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user != null && product != null) {
            CartId cartId = new CartId();
            cartId.setUser(user);
            cartId.setProduct(product);
            Cart cart = cartRepository.findById(cartId).orElse(null);

            if (cart != null) {
                CartDTO cartDTO = (CartDTO) cartMapper.toDTO(cart);
                return cartDTO;
            }
        }

        return null;
    }


    public List<CartDTO> addToCart(Integer userId, Integer productId, Integer quantity) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user != null && product != null) {
            CartDTO existingCartDTO = getCartByUserIdAndProductId(userId, productId);

            if (existingCartDTO != null) {
                Integer newQuantity = existingCartDTO.getQuantity() + quantity;
                existingCartDTO.setQuantity(newQuantity);
                Cart cart = mapper.toEntity(existingCartDTO, user, product);
                Cart savedCart = cartRepository.save(cart);
                CartDTO savedCartDTO = mapper.toDTO(savedCart);
                return getAllByIdUser(userId);
            } else {
                CartDTO cartDTO = new CartDTO();
                cartDTO.setUserId(userId);
                cartDTO.setProduct(productMapper.toDTO(product));
                cartDTO.setQuantity(quantity);
                Cart cart = mapper.toEntity(cartDTO, user, product);
                Cart savedCart = cartRepository.save(cart);
                CartDTO savedCartDTO = (CartDTO) cartMapper.toDTO(savedCart);
                return getAllByIdUser(userId);
            }
        }

        return null;
    }

    public List<CartDTO> updateCart(Integer userId, Integer productId, Integer quantity) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user != null && product != null) {
            CartDTO existingCartDTO = getCartByUserIdAndProductId(userId, productId);

            if (existingCartDTO != null) {
                existingCartDTO.setQuantity(quantity);
                Cart cart = mapper.toEntity(existingCartDTO, user, product);
                Cart savedCart = cartRepository.save(cart);
                return getAllByIdUser(userId);
            }
        }
        return null;
    }
    
    public void deleteCart(Integer userId, Integer productId) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user != null && product != null) {
            List<Cart> list = cartRepository.findByIdUserId(userId);
            for(Cart cart: list) {
            	if(cart.getId().getProduct().getId().equals(productId)) {
            		cartRepository.delete(cart);
            		return;
            	}
            }
        }
    }
    
    public void deleteCartByUserId(Integer userId) {
    	List<Cart> carts = cartRepository.findByIdUserId(userId);
    	for(Cart cart: carts) {
            cartRepository.deleteById(cart.getId());
    	}
    }
                
}