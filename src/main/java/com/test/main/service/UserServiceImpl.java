package com.test.main.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.test.main.model.dto.UserDto;
import com.test.main.model.entity.Role;
import com.test.main.model.entity.User;
import com.test.main.model.mapper.UserMapper;
import com.test.main.model.request.UpdateUserRequest;
import com.test.main.repository.RoleRepository;
import com.test.main.repository.UserRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	private UserRepository userRepository;

	private RoleRepository roleRepository;
	
	
	@Override
	public List<UserDto> getUsers() {
		List<User> userList = userRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<>();
		for (User user : userList) {
			UserDto userDto = userMapper.toDto(user);
			userDto.setAddresses(user.getAddresses());
			userDtoList.add(userDto);
		}

		return userDtoList;
	}

	@Override
	public UserDto getUserDetail(Long id) {
		User user = userRepository.getUserDetialsById(id);
		UserDto userDto = userMapper.toDto(user);
		userDto.setAddresses(user.getAddresses());
		return userDto;
	}

	@Override
	public UserDto update(Long id, UpdateUserRequest request) {
		User user = userRepository.getUserDetialsById(id);
		if (user == null) {
			throw new RuntimeException("User id does not exist!");
		}
		user.setUsername(request.getUserName());
		user.setPassword(request.getPassword());
		user.setMobileNumber(request.getMobileNumber());
	
		Optional<Role> role = roleRepository.findByrName(request.getRoleName());
		if (role.isEmpty()) {
			Role newRole = new Role();
			newRole.setRoleName(request.getRoleName());
			roleRepository.save(newRole);
			Set<Role> roles = user.getRoles();
			roles.add(newRole);
			user.setRoles(roles);
		} else {
			Set<Role> roleList = user.getRoles();
			roleList.add(role.get());
			user.setRoles(roleList);
		}
		User updatedUser = userRepository.save(user);
		UserDto userDto = userMapper.toDto(updatedUser);
		return userDto;
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.getUserDetialsById(id);
		userRepository.delete(user);
	}

	@Override
	public UserDto createAdmin(UserDto userDto) {
		User user = userMapper.toEntity(userDto);
		Role role = roleRepository.findByRoleName("ROLE_ADMIN");
		user.setRoles(Collections.singleton(role));
		User savedUser = userRepository.save(user);
		UserDto createdUserDto = userMapper.toDto(savedUser);
		return createdUserDto;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = userMapper.toEntity(userDto);
		Role role= roleRepository.findByRoleName("ROLE_USER");
		user.setRoles(Collections.singleton(role));
		User savedUser = userRepository.save(user);
		UserDto createdUserDto = userMapper.toDto(savedUser);
		return createdUserDto;
	}

	@Override
	public User getUserDetailsById(Long id) {
		return userRepository.getById(id);
	}


}
