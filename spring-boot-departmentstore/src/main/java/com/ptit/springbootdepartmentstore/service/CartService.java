package com.ptit.springbootdepartmentstore.service;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.request.RequestAddCart;
import com.ptit.springbootdepartmentstore.dto.request.RequestGetCart;
import com.ptit.springbootdepartmentstore.dto.response.ActionStatus;
import com.ptit.springbootdepartmentstore.dto.response.ProductInCart;
import com.ptit.springbootdepartmentstore.dto.response.ResponseGetCart;
import com.ptit.springbootdepartmentstore.entity.Product;

@Component
public interface CartService {
	
	public ActionStatus addProductToCart (RequestAddCart addCart);
	
	public ResponseGetCart getCart (RequestGetCart getCart);
	
	public ProductInCart convertProduct (Product product, Integer quantily);

}
