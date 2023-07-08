package com.ptit.springbootdepartmentstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.PermissionDTO;
import com.ptit.springbootdepartmentstore.entity.Permission;

@Component
public class PermissionMapper implements Mapper<Permission, PermissionDTO>{
	
	@Override
	public PermissionDTO toDTO(Permission permisson) {
		PermissionDTO permissionDTO = new PermissionDTO();
		permissionDTO.setId(permisson.getId());
		permissionDTO.setPermissionName(permisson.getName());
		return permissionDTO;
	}
	
	@Override
	public Permission toEntity(PermissionDTO permissionDTO) {
		Permission permission = new Permission();
		permission.setId(permissionDTO.getId());
		permission.setName(permissionDTO.getPermissionName());
		return permission;
	}

	@Override
	public List<PermissionDTO> toListDTO(List<? extends Permission> listEntity) {
		return listEntity
    			.stream()
    			.map(this::toDTO)
    			.collect(Collectors.toList());
	}

	@Override
	public List<Permission> toListEntity(List<? extends PermissionDTO> listDTO) {
		return listDTO
    			.stream()
    			.map(this::toEntity)
    			.collect(Collectors.toList());
	}

}
