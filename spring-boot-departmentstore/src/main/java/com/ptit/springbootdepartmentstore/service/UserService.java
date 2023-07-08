package com.ptit.springbootdepartmentstore.service;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.UserDTO;
import com.ptit.springbootdepartmentstore.dto.UserMobileDTO;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.mapper.BaseMapperFactory;
import com.ptit.springbootdepartmentstore.mapper.ConstantMapper;
import com.ptit.springbootdepartmentstore.mapper.Mapper;
import com.ptit.springbootdepartmentstore.mapper.MapperFactory;
import com.ptit.springbootdepartmentstore.mapper.component.UserMapper;
import com.ptit.springbootdepartmentstore.mapper.component.UserMobileMapper;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private UserMapper userMapper;
//	
//	@Autowired
//	private UserMobileMapper userMobileMapper;
	
	@Autowired
	private MailService mailService;
	
	private BaseMapperFactory mapperFactory = new MapperFactory();

	private Mapper userMapper = mapperFactory.Choose(ConstantMapper.USER);
	
	private Mapper userMobileMapper = mapperFactory.Choose(ConstantMapper.USERMOBILE);

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
		return (UserDTO) userMapper.toDTO(user);
	}

	@Transactional(rollbackOn = Exception.class)
	public UserDTO createUser(UserDTO userDTO) {
		User user = (User) userMapper.toEntity(userDTO);
		User savedUser = userRepository.save(user);
		if(savedUser.getEmail() != null)
			mailService.sendSimpleEmail(
					savedUser.getEmail(), 
					"Wellcome To Online Shop", 
					"Username : " + savedUser.getName() + " And Password : " + savedUser.getPassword());
		return (UserDTO) userMapper.toDTO(savedUser);
	}
	
	@Transactional(rollbackOn = Exception.class)
	public UserMobileDTO createUserMobile(UserMobileDTO userDTO) {
		User user = (User) userMobileMapper.toEntity(userDTO);
		User savedUser = userRepository.save(user);
		if(savedUser.getEmail() != null)
			mailService.sendSimpleEmail(
					savedUser.getEmail(), 
					"Wellcome To Online Shop", 
					"Username : " + savedUser.getName() + " And Password : " + savedUser.getPassword());
		return (UserMobileDTO) userMobileMapper.toDTO(savedUser);
	}

	@Transactional(rollbackOn = Exception.class)
	public UserDTO updateUser(UserDTO userDTO) {
		User user = userRepository.findById(userDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
		user = (User) userMapper.toEntity(userDTO);
		User savedUser = userRepository.save(user);
		if(savedUser.getEmail() != null)
			mailService.sendSimpleEmail(
					savedUser.getEmail(), 
					"Update Account Online Shop", 
					"Username : " + savedUser.getName() + " And Password : " + savedUser.getPassword());
		return (UserDTO) userMapper.toDTO(savedUser);
	}

	@Transactional(rollbackOn = Exception.class)
	public void deleteUser(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
		if(user.getEmail() != null)
			mailService.sendSimpleEmail(
					user.getEmail(), 
					"Delete Account Online Shop", 
					"Username : " + user.getName() + " And Password : " + user.getPassword() + " Have Been Delete.");
		userRepository.delete(user);
	}

	public UserDTO authenticate(String name, String password) {
		User user = userRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("User not found"));
		if (user.getPassword().equals(password)) {
			return (UserDTO) userMapper.toDTO(user);
		} else {
			throw new IllegalArgumentException("Invalid password");
		}
	}
	
	public String randomStringGenerator () {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
	}
	
	public String forgotPassword(String mail) {
		User user = userRepository.findByEmail(mail).orElseThrow(() -> new EntityNotFoundException("User not found"));
		String newPass = randomStringGenerator();
		user.setPassword(newPass);
		User savedUser = userRepository.save(user);
		if(savedUser.getEmail() != null)
			mailService.sendSimpleEmail(
					savedUser.getEmail(), 
					"Forget Account Online Shop", 
					"Username : " + savedUser.getName() + " And Random Password : " + savedUser.getPassword());
		return user.getEmail().replaceAll("[0-4]", "*");
	}
}