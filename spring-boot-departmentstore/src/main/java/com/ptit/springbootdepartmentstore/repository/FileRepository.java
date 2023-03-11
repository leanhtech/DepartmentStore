package com.ptit.springbootdepartmentstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Image;

public interface FileRepository extends JpaRepository<Image, Integer> {

}
