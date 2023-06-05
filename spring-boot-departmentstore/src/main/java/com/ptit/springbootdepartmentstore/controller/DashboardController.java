package com.ptit.springbootdepartmentstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.springbootdepartmentstore.dto.DashBoardDTO;
import com.ptit.springbootdepartmentstore.service.DashBoardService;

@RestController
@RequestMapping("/DashBoard")
public class DashboardController {
	
	@Autowired
	private DashBoardService dashBoardService;
	
	@GetMapping
	public DashBoardDTO getDashBoardTop3 () {
		return dashBoardService.getDashBoard();
	}

}
