package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/apiuser")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	/*
	 * Not Done
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody User login) {
		return userService.Login(login.getUserName(), login.getPassword());
	}
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@PostMapping("/update")
	public String update(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@GetMapping("/getalluser")
	public List<User> findAllUser(){
		return userService.getUsers();
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public void deleteEmployee(@PathVariable int id) {
		userService.deleteUser(id);
	}
	*/
}
