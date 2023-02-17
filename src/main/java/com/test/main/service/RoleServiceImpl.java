package com.test.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.main.model.dto.RoleDto;
import com.test.main.model.entity.Role;
import com.test.main.model.mapper.RoleMapper;
import com.test.main.model.request.RoleRequest;
import com.test.main.repository.RoleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

	private RoleMapper roleMapper;

	private RoleRepository roleRepository;

	@Override
	public RoleDto create(RoleDto roleDto) {
		
		Role role = roleMapper.toEntity(roleDto);
		Role savedRole = roleRepository.save(role);
		RoleDto savedRoleDto = roleMapper.toDto(savedRole);
		return savedRoleDto;
	}

	@Override
	public List<RoleDto> getRoles() {
		List<RoleDto> roleDtoList = new ArrayList<>();
		List<Role> roleList = roleRepository.findAll();
		for (Role role : roleList) {
			RoleDto roleDto = roleMapper.toDto(role);
			roleDtoList.add(roleDto);
		}
		return roleDtoList;
	}

	@Override
	public RoleDto getRoleDetail(Long id) {
		Role role = roleRepository.getRoleDetailById(id);
		RoleDto roleDto = roleMapper.toDto(role);
		return roleDto;
	}

	@Override
	public RoleDto updateRole(Long id, RoleRequest request) {
		Role role = roleRepository.getRoleDetailById(id);
		role.setRoleName(request.getRoleName());
		Role savedRole = roleRepository.save(role);
		RoleDto roleDto = roleMapper.toDto(savedRole);
		return roleDto;
	}

	@Override
	public void delete(Long id) {
		Role role = roleRepository.getRoleDetailById(id);
		roleRepository.delete(role);
	}

	@Override
	public Role getUserByRoleName(String roleName) {
		Role role = roleRepository.findByRoleName(roleName);
		return role;
	}

}
