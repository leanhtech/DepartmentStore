package com.ptit.springbootdepartmentstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.entity.Brand;
import com.ptit.springbootdepartmentstore.repository.BrandRepository;
import com.ptit.springbootdepartmentstore.service.BrandService;


@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	BrandRepository brandRepository;

	@Override
	public Brand getBrandById(int id) {
		return brandRepository.findById(id).orElse(null);
	}

	@Override
	public Brand getBrandByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Brand> getAllBrand() {
		// TODO Auto-generated method stub
		return null;
	}

}
