package com.ptit.springbootdepartmentstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
