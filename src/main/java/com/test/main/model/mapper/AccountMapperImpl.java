package com.test.main.model.mapper;

import org.springframework.stereotype.Component;

import com.test.main.model.dto.AccountDto;
import com.test.main.model.entity.Account;
import com.test.main.model.request.AccountRequest;
import com.test.main.model.rsponse.AccountResponse;

@Component
public class AccountMapperImpl implements AccountMapper{

	@Override
	public AccountDto toDto(AccountRequest request) {
		AccountDto accountDto = new AccountDto();
		accountDto.setAmount(request.getAmount());
		return accountDto;
	}

	@Override
	public AccountResponse toResponse(AccountDto accountDto) {
		AccountResponse response = new AccountResponse();
		response.setId(accountDto.getId());
		response.setAmount(accountDto.getAmount());
		return response;
	}

	@Override
	public Account toEntity(AccountDto accountDto) {
		Account account = new Account();
		account.setId(accountDto.getId());
		account.setAmount(accountDto.getAmount());
		return account;
	}

	@Override
	public AccountDto toDto(Account account) {
		if(account == null) {
			return null;
		}
		AccountDto accountDto = new AccountDto();
		accountDto.setId(account.getId());
		accountDto.setAmount(account.getAmount());
		return accountDto;
	}

	@Override
	public AccountResponse toResponse(Account account) {
		if(account == null) {
			return null;
		}
		AccountResponse response = new AccountResponse();
		response.setId(account.getId());
		response.setAmount(account.getAmount());
		return response;
	}

}
