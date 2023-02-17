package com.test.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.main.model.entity.User;
import com.test.main.model.exception.BadRequestException;
import com.test.main.repository.UserRepository;
import com.test.main.security.UserPrincipal;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	

	public UserDetailServiceImpl() {
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUserName(username)
						.orElseThrow(() -> new BadRequestException("Username not found!"));
		return UserPrincipal.build(user);
	}

}
