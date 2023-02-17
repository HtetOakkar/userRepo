package com.test.main.model.mapper;

import org.springframework.stereotype.Component;

import com.test.main.model.dto.RoleDto;
import com.test.main.model.entity.Role;
import com.test.main.model.request.RoleRequest;
import com.test.main.model.rsponse.RoleResponse;

import lombok.NonNull;

@Component
public class RoleMapperImpl implements RoleMapper {

	@Override
	public RoleResponse toResponse(RoleDto roleDto) {
		RoleResponse response = new RoleResponse();
		response.setId(roleDto.getId());
		response.setRoleName(roleDto.getRoleName());
		return response;
	}

	@Override
	public RoleDto toDto(@NonNull Role savedRole) {

		RoleDto roleDto = new RoleDto();
		roleDto.setId(savedRole.getId());
		roleDto.setRoleName(savedRole.getRoleName());

		return roleDto;
	}

	@Override
	public RoleDto toDto(RoleRequest request) {
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleName(request.getRoleName());
		return roleDto;
	}

	@Override
	public Role toEntity(RoleDto roleDto) {
		Role role = new Role();
		role.setRoleName(roleDto.getRoleName());
		return role;
	}

}
