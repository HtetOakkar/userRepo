package com.test.main.model.mapper;

import com.test.main.model.dto.UserDto;
import com.test.main.model.entity.User;
import com.test.main.model.request.UserRequest;
import com.test.main.model.rsponse.UserResponse;

public interface UserMapper{

	UserResponse toResponse(UserDto userDto);

	UserDto toDto(User user);

	UserDto toDto(UserRequest request);

	User toEntity(UserDto userDto);

	UserResponse toResponse(User user);
	
}
