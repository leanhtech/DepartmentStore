package com.ptit.springbootdepartmentstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Permission;
import com.ptit.springbootdepartmentstore.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public List<User> findByStatus(String stats);
	public List<User> findByPermission(Permission permission);
}
