package com.test.main.model.mapper;

import com.test.main.model.dto.RoleDto;
import com.test.main.model.entity.Role;
import com.test.main.model.request.RoleRequest;
import com.test.main.model.rsponse.RoleResponse;

public interface RoleMapper {

	RoleResponse toResponse(RoleDto roleDto);

	RoleDto toDto(Role role);

	RoleDto toDto(RoleRequest request);

	Role toEntity(RoleDto roleDto);

}
