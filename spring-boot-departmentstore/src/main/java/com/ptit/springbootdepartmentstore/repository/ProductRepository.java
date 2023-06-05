package com.ptit.springbootdepartmentstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findFirst8ByOrderBySold();
	
	List<Product> findTop3ByOrderByQuantity();
}
