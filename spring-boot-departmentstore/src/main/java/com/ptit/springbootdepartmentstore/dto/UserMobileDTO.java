package com.ptit.springbootdepartmentstore.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMobileDTO {

	private Integer id;

	private String userName;

	private String password;

	private String firstName;

	private String lastName;
	
	private Integer imageId;

	private String email;

	private String phone;

	private Date createDate;

	private String status;

	private AddressDTO address;

	private Integer permissionId;
	
	private String imageBase64;
}
