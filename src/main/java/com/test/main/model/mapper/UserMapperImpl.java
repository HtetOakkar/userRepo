package com.test.main.model.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.test.main.model.dto.UserDto;
import com.test.main.model.entity.Account;
import com.test.main.model.entity.Address;
import com.test.main.model.entity.Role;
import com.test.main.model.entity.User;
import com.test.main.model.request.UserRequest;
import com.test.main.model.rsponse.UserAccountResponse;
import com.test.main.model.rsponse.UserAddressResponse;
import com.test.main.model.rsponse.UserResponse;
import com.test.main.model.rsponse.UserRoleResponse;

import lombok.NonNull;

@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserResponse toResponse(UserDto userDto) {
		UserResponse response = new UserResponse();
		response.setId(userDto.getId());
		response.setUserName(userDto.getUserName());
		response.setPassword(userDto.getPassword());
		response.setMobileNumber(userDto.getMobileNumber());
		Set<UserRoleResponse> userRoleResponse = new HashSet<>();
		Set<Role> roleList = userDto.getRoles();
		for (Role role : roleList) {
			UserRoleResponse roleResponse = new UserRoleResponse();
			roleResponse.setRoleId(role.getId());
			roleResponse.setRoleName(role.getRoleName());
			userRoleResponse.add(roleResponse);
		}
		response.setRoles(userRoleResponse);

		List<UserAddressResponse> userAddressResponses = new ArrayList<>();
		List<Address> addresses = userDto.getAddresses();
		if (addresses != null) {
			for (Address address : addresses) {
				UserAddressResponse addressResponse = new UserAddressResponse();
				addressResponse.setAddressId(address.getId());
				addressResponse.setStreet(address.getStreet());
				addressResponse.setCity(address.getCity());
				addressResponse.setType(address.getType());
				userAddressResponses.add(addressResponse);
			}
			response.setAddresses(userAddressResponses);
		} else {
			response.setAddresses(null);
		}
		Account account = userDto.getAccount();
		if (account == null) {
			response.setAccount(null);
		} else {
			UserAccountResponse accountResponse = new UserAccountResponse();
			accountResponse.setAccountId(account.getId());
			accountResponse.setAmount(account.getAmount());
			response.setAccount(accountResponse);
		}
		return response;
	}

	@Override
	public UserDto toDto(@NonNull User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUserName(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setMobileNumber(user.getMobileNumber());
		userDto.setRoles(user.getRoles());
		userDto.setAddresses(user.getAddresses());
		userDto.setAccount(user.getAccount());
		return userDto;
	}

	@Override
	public UserDto toDto(UserRequest request) {
		UserDto userDto = new UserDto();
		userDto.setUserName(request.getUserName());
		userDto.setPassword(request.getPassword());
		userDto.setMobileNumber(request.getMobileNumber());
		return userDto;
	}

	@Override
	public User toEntity(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setMobileNumber(userDto.getMobileNumber());
		user.setAddresses(userDto.getAddresses());
		user.setAccount(userDto.getAccount());
		return user;
	}

	@Override
	public UserResponse toResponse(User user) {
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setUserName(user.getUsername());
		response.setPassword(user.getPassword());
		response.setMobileNumber(user.getMobileNumber());
		Set<UserRoleResponse> userRoleResponse = new HashSet<>();
		Set<Role> roleList = user.getRoles();
		for (Role role : roleList) {
			UserRoleResponse roleResponse = new UserRoleResponse();
			roleResponse.setRoleId(role.getId());
			roleResponse.setRoleName(role.getRoleName());
			userRoleResponse.add(roleResponse);
		}
		response.setRoles(userRoleResponse);
		List<UserAddressResponse> userAddressResponses = new ArrayList<>();
		List<Address> addresses = user.getAddresses();
		if (addresses != null) {
			for (Address address : addresses) {
				UserAddressResponse addressResponse = new UserAddressResponse();
				addressResponse.setAddressId(address.getId());
				addressResponse.setStreet(address.getStreet());
				addressResponse.setCity(address.getCity());
				addressResponse.setType(address.getType());
				userAddressResponses.add(addressResponse);
			}
			response.setAddresses(userAddressResponses);
		} else {
			response.setAddresses(null);
		}
		Account account = user.getAccount();
		if (account == null) {
			response.setAccount(null);
		} else {
			UserAccountResponse accountResponse = new UserAccountResponse();
			accountResponse.setAccountId(account.getId());
			accountResponse.setAmount(account.getAmount());
			response.setAccount(accountResponse);
		}
		return response;
	}

}
