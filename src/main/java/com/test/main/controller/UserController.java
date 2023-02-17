package com.test.main.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.main.model.dto.UserDto;
import com.test.main.model.entity.Role;
import com.test.main.model.entity.User;
import com.test.main.model.mapper.UserMapper;
import com.test.main.model.request.UpdateUserRequest;
import com.test.main.model.request.UserRequest;
import com.test.main.model.rsponse.UserResponse;
import com.test.main.service.RoleService;
import com.test.main.service.UserService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

	private UserMapper userMapper;

	private UserService userService;

	private RoleService roleService;
	
	private BCryptPasswordEncoder encoder;

	@PostMapping(path="/users/admin/create")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserResponse createUserRoleAdmin(@RequestBody @Valid UserRequest request) {
		request.setPassword(encoder.encode(request.getPassword()));
		UserDto userDto = userMapper.toDto(request);
		System.out.println(request.getMobileNumber());
		UserDto savedUserDto = userService.createAdmin(userDto);
		UserResponse response = userMapper.toResponse(savedUserDto);
		return response;
	}
	
	@PostMapping(path="/users/user/create")
	@PreAuthorize("hasRole('ROLE_USER')")
	public UserResponse createUserRoleUser(@RequestBody @Valid UserRequest request) {
		request.setPassword(encoder.encode(request.getPassword()));
		UserDto userDto = userMapper.toDto(request);
		UserDto savedUserDto = userService.createUser(userDto);
		UserResponse response = userMapper.toResponse(savedUserDto);
		return response;
	}

	@GetMapping(path="/users/user/get")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UserResponse> getusers() {
		List<UserResponse> responseList = new ArrayList<>();
		List<UserDto> userDtoList = userService.getUsers();
		for (UserDto userDto : userDtoList) {
			UserResponse response = userMapper.toResponse(userDto);
			responseList.add(response);
		}
		return responseList;
	}

	@GetMapping(path = "/users/user/{id}/get")
	@PreAuthorize("hasRole('ROLE_USER')")
	public UserResponse getUserDetails(@PathVariable Long id) {
		UserDto userDto = userService.getUserDetail(id);
		UserResponse response = userMapper.toResponse(userDto);
		return response;
	}

	@PutMapping(path = "/users/user/{id}/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserResponse updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) {
		
		request.setPassword(encoder.encode(request.getPassword()));
		UserDto userDto = userService.update(id, request);
		UserResponse response = userMapper.toResponse(userDto);
		return response;
	}

	@DeleteMapping(path = "/users/user/{id}/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROlE_USER')")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("User Successfully Deleted", HttpStatus.OK);
	}
	
	@GetMapping(path="/users/user/get/roles")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Set<UserResponse> getUsersByRoleName(@RequestParam(value = "name") String roleName) {
		Set<UserResponse> responseList = new HashSet<>();
		Role role = roleService.getUserByRoleName(roleName);
		Set<User> users = role.getUsers();
		for (User user : users) {
			UserResponse response = userMapper.toResponse(user);
			responseList.add(response);
		}
		return responseList;
	}
	
}
