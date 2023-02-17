package com.test.main.model.mapper;

import com.test.main.model.dto.AccountDto;
import com.test.main.model.entity.Account;
import com.test.main.model.request.AccountRequest;
import com.test.main.model.rsponse.AccountResponse;

public interface AccountMapper {

	AccountDto toDto(AccountRequest request);

	AccountResponse toResponse(AccountDto accountDto);

	Account toEntity(AccountDto accountDto);

	AccountDto toDto(Account account);

	AccountResponse toResponse(Account account);
	
}
