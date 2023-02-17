package com.test.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.main.model.dto.RoleDto;
import com.test.main.model.mapper.RoleMapper;
import com.test.main.model.request.RoleRequest;
import com.test.main.model.rsponse.RoleResponse;
import com.test.main.service.RoleService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class RoleController {

	RoleService roleService;

	RoleMapper roleMapper;

	@PostMapping(path="/users/roles/add")
	public RoleResponse createRole(@RequestBody RoleRequest request) {
		RoleDto roleDto = roleMapper.toDto(request);
		RoleDto createdRoleDto = roleService.create(roleDto);
		RoleResponse response = roleMapper.toResponse(createdRoleDto);
		return response;
	}

	@GetMapping(path="/users/roles/get")
	public List<RoleResponse> getRoles() {
		List<RoleResponse> responseList = new ArrayList<>();
		List<RoleDto> roleDtoList = roleService.getRoles();
		for (RoleDto roleDto : roleDtoList) {
			RoleResponse response = roleMapper.toResponse(roleDto);
			responseList.add(response);
		}
		return responseList;
	}
	
	@GetMapping(path="/users/roles/{id}/get")
	public RoleResponse getRoleDetail(@PathVariable Long id) {
		RoleDto roleDto = roleService.getRoleDetail(id);
		RoleResponse response = roleMapper.toResponse(roleDto);
		return response;
	}
	
	@PutMapping(path="/users/roles/{id}/update")
	public RoleResponse updateRole(@PathVariable Long id, @RequestBody RoleRequest request) {
		RoleDto roleDto = roleService.updateRole(id, request);
		RoleResponse response = roleMapper.toResponse(roleDto);
		return response;
	}

	@DeleteMapping(path="/users/roles/{id}/delete")
	public ResponseEntity<?> deleteRole(@PathVariable Long id) {
		roleService.delete(id);
		return new ResponseEntity<>("Role Successfully Deleted!", HttpStatus.OK);
	}
}
