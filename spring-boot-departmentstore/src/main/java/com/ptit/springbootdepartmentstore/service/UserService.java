package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.UserDTO;
import com.ptit.springbootdepartmentstore.dto.UserMobileDTO;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.mapper.UserMapper;
import com.ptit.springbootdepartmentstore.mapper.UserMobileMapper;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserMobileMapper userMobileMapper;

	public List<UserDTO> getAllUser() {
		List<User> users = userRepository.findAll();
		return userMapper.toListDTO(users);
	}
	
	public List<UserMobileDTO> getAllMobileUser() {
		List<User> users = userRepository.findAll();
		return userMobileMapper.toListDTO(users);
	}

	public UserDTO getUser(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
		return userMapper.toDTO(user);
	}

	@Transactional(rollbackOn = Exception.class)
	public UserDTO createUser(UserDTO userDTO) {
		User user = userMapper.toEntity(userDTO);
		User savedUser = userRepository.save(user);
		System.out.println(savedUser.getId());
		return userMapper.toDTO(savedUser);
	}
	
	@Transactional(rollbackOn = Exception.class)
	public UserMobileDTO createUserMobile(UserMobileDTO userDTO) {
		User user = userMobileMapper.toEntity(userDTO);
		User savedUser = userRepository.save(user);
		System.out.println(savedUser.getId());
		return userMobileMapper.toDTO(savedUser);
	}

	@Transactional(rollbackOn = Exception.class)
	public UserDTO updateUser(UserDTO userDTO) {
		User user = userRepository.findById(userDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
		user = userMapper.toEntity(userDTO);
		User savedUser = userRepository.save(user);
		return userMapper.toDTO(savedUser);
	}

	@Transactional(rollbackOn = Exception.class)
	public void deleteUser(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
		userRepository.delete(user);
	}

	public UserDTO authenticate(String name, String password) {
		User user = userRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("User not found"));
		if (user.getPassword().equals(password)) {
			return userMapper.toDTO(user);
		} else {
			throw new IllegalArgumentException("Invalid password");
		}
	}
}