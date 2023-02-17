package com.test.main.service;

import java.util.List;

import com.test.main.model.dto.UserDto;
import com.test.main.model.entity.User;
import com.test.main.model.request.UpdateUserRequest;

public interface UserService {

	List<UserDto> getUsers();

	UserDto getUserDetail(Long id);

	UserDto update(Long id, UpdateUserRequest request);

	void deleteUser(Long id);

	UserDto createAdmin(UserDto userDto);

	UserDto createUser(UserDto userDto);

	User getUserDetailsById(Long id);


}
