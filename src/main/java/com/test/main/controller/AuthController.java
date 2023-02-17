package com.test.main.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.main.model.request.UserLoginRequest;
import com.test.main.model.rsponse.AuthResponse;
import com.test.main.model.rsponse.JwtAuthResponse;
import com.test.main.security.JwtTokenProvider;

@RestController
@RequestMapping(path="/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;


	@PostMapping(path = "/user/login")
	public ResponseEntity<?> loginUser(@RequestBody @Valid UserLoginRequest request) {
		Date expiredAt = new Date((new Date()).getTime() + 86400 * 1000);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String accessToken = tokenProvider.generateJwtToken(authentication);
			
			return ResponseEntity.ok(new JwtAuthResponse(accessToken, expiredAt));
		}
		
		return new ResponseEntity<>(new AuthResponse(false, "Username or password incorrect."), HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(path = "/admin/login")
	public ResponseEntity<?> loginAdmin(@RequestBody @Valid UserLoginRequest request) {
		Date expiredAt = new Date((new Date()).getTime() + 86400 * 1000);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String accessToken = tokenProvider.generateJwtToken(authentication);
			
			return ResponseEntity.ok(new JwtAuthResponse(accessToken, expiredAt));
		}
		
		return new ResponseEntity<>(new AuthResponse(false, "Username or password incorrect."), HttpStatus.NOT_FOUND);
	}
}
