package com.test.main.service;

import java.util.List;

import com.test.main.model.dto.RoleDto;
import com.test.main.model.entity.Role;
import com.test.main.model.request.RoleRequest;

public interface RoleService {

	RoleDto create(RoleDto roleDto);

	List<RoleDto> getRoles();

	RoleDto getRoleDetail(Long id);

	RoleDto updateRole(Long id, RoleRequest request);

	void delete(Long id);

	Role getUserByRoleName(String roleName);

}
