package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.entity.Brand;

@Component
public interface BrandService {
	
	public Brand getBrandById(int id);
	
	public Brand getBrandByName(String name);
	
	public List<Brand> getAllBrand();
}
