package com.ptit.springbootdepartmentstore.mapper.component;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.AddressDTO;
import com.ptit.springbootdepartmentstore.entity.Address;
import com.ptit.springbootdepartmentstore.mapper.Mapper;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Component
public class AddressMapper implements Mapper <Address, AddressDTO>{

	@Autowired
    private UserRepository userRepository;

	@Override
    public AddressDTO toDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setAddressGeneral(address.getAddressGeneral());
        addressDTO.setAddressSpecific(address.getAddressSpecific());
        if(address.getUser() != null)
            addressDTO.setUserId(address.getUser().getId());
        return addressDTO;
    }
	
	@Override
    public Address toEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setAddressGeneral(addressDTO.getAddressGeneral());
        address.setAddressSpecific(addressDTO.getAddressSpecific());
        if(addressDTO.getUserId() != null)
            address.setUser(userRepository.findById(addressDTO.getUserId())
                    .orElse(null));
        return address;
    }

    public List<Integer> toListId(List<? extends Address> addresses) {
        return addresses.stream().map(Address::getId).collect(Collectors.toList());
    }

	@Override
	public List<AddressDTO> toListDTO(List<? extends Address> listEntity) {
		return listEntity
    			.stream()
    			.map(this::toDTO)
    			.collect(Collectors.toList());
	}

	@Override
	public List<Address> toListEntity(List<? extends AddressDTO> listDTO) {
		return listDTO
    			.stream()
    			.map(this::toEntity)
    			.collect(Collectors.toList());
	}

}