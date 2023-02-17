package com.test.main.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.main.model.dto.AccountDto;
import com.test.main.model.entity.Account;
import com.test.main.model.entity.User;
import com.test.main.model.mapper.AccountMapper;
import com.test.main.model.request.AccountRequest;
import com.test.main.model.rsponse.AccountResponse;
import com.test.main.security.UserPrincipal;
import com.test.main.service.AccountService;
import com.test.main.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AccountController {
	
	private AccountMapper accountMapper;
	
	private AccountService accountService;
	
	private UserService userService;
	
	@PostMapping(path = "/users/account/create")
	@PreAuthorize("hasRole('ROLE_USER')")
	public AccountResponse createAccount(@RequestBody AccountRequest request) {
		AccountDto accountDto = accountMapper.toDto(request);
		AccountDto createdAccount = accountService.createAccount(accountDto);
		AccountResponse response = accountMapper.toResponse(createdAccount);
		return response;
	}
	
	@GetMapping(path = "/users/account/get")
	@PreAuthorize("hasRole('ROLE_USER')")
	public AccountResponse getAmount() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		User user = userService.getUserDetailsById(userPrincipal.getId());
		Account account =  user.getAccount();
		AccountResponse response = accountMapper.toResponse(account);
		return response;
	}
	
	@PutMapping(path = "/users/account/{id}/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AccountResponse addAmount(@PathVariable(value = "id") Long id, @RequestBody AccountRequest request) {
		AccountDto accountDto = accountService.addAmount(id, request);
		AccountResponse response = accountMapper.toResponse(accountDto);
		return response;
	}
	
}
