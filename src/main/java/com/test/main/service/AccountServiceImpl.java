package com.test.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.test.main.model.dto.AccountDto;
import com.test.main.model.entity.Account;
import com.test.main.model.entity.User;
import com.test.main.model.mapper.AccountMapper;
import com.test.main.model.request.AccountRequest;
import com.test.main.repository.AccountRepository;
import com.test.main.repository.UserRepository;
import com.test.main.security.UserPrincipal;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		User user = userRepository.getById(userPrincipal.getId());
		Account account = accountMapper.toEntity(accountDto);
		account.setUser(user);
		Account savedAccount = accountRepository.save(account);
		AccountDto savedAccountDto = accountMapper.toDto(savedAccount);
		return savedAccountDto;
	}

	@Override
	public AccountDto addAmount(Long userId, AccountRequest request) {
		User user = userRepository.findById(userId).get();
		if (user==null) {
			throw new RuntimeException("User with " + userId + " not found!");
		}
		Account account = user.getAccount();
		account.setAmount(request.getAmount()+account.getAmount());
		Account updatedAccount = accountRepository.save(account);
		AccountDto accountDto = accountMapper.toDto(updatedAccount);
		return accountDto;
	}
	
}
