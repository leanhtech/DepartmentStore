package com.ptit.springbootdepartmentstore.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.AddressDTO;
import com.ptit.springbootdepartmentstore.entity.Address;
import com.ptit.springbootdepartmentstore.mapper.AddressMapper;
import com.ptit.springbootdepartmentstore.mapper.BaseMapperFactory;
import com.ptit.springbootdepartmentstore.mapper.Constant;
import com.ptit.springbootdepartmentstore.mapper.Mapper;
import com.ptit.springbootdepartmentstore.mapper.MapperFactory;
import com.ptit.springbootdepartmentstore.repository.AddressRepository;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;

	//@Autowired
	//private AddressMapper addressMapper;
	
	BaseMapperFactory mapperFacory = new MapperFactory();
	Mapper<Address, AddressDTO> addressMapper = (Mapper<Address, AddressDTO>) mapperFacory.Choose(Constant.ADDRESS);
	

	public List<String> getListTextAddress(List<AddressDTO> addressDTOs) {
		return addressDTOs.stream()
				.map(addressDTO -> addressDTO.getAddressSpecific() + " " + addressDTO.getAddressGeneral())
				.collect(Collectors.toList());
	}

	public List<Address> getListAddress(List<Integer> ids) {
		return addressRepository.findAllById(ids);
	}
	
	public List<AddressDTO> getAllAddress() {
		mailService.sendSimpleEmail("katoritakuofficial@gmail.com", "Test Mail", "Alo dang lay list address");
		return addressMapper.toListDTO(addressRepository.findAll());
	}

	public Address getAddressEntity(int id) {
		return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found"));
	}

	@Transactional(rollbackOn = Exception.class)
	public AddressDTO saveAddress(AddressDTO addressDTO) {
		Address address = addressMapper.toEntity(addressDTO);
		Address savedAddress = addressRepository.save(address);
		return addressMapper.toDTO(savedAddress);
	}

	@Transactional(rollbackOn = Exception.class)
	public AddressDTO updateAddress(AddressDTO addressDTO) {
		Address address = addressRepository.findById(addressDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("Address not found"));
		address.setAddressGeneral(addressDTO.getAddressGeneral());
		address.setAddressSpecific(addressDTO.getAddressSpecific());
		address.setUser(userRepository.findById(addressDTO.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("User not found")));
		Address updatedAddress = addressRepository.save(address);
		return addressMapper.toDTO(updatedAddress);
	}

	@Transactional(rollbackOn = Exception.class)
	public void deleteAddress(int id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Address not found"));
		addressRepository.delete(address);
	}

	public AddressDTO getAddress(int id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Address not found"));
		return addressMapper.toDTO(address);
	}
}