package com.test.main.service;

import com.test.main.model.dto.AccountDto;
import com.test.main.model.request.AccountRequest;

public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);

	AccountDto addAmount(Long userId, AccountRequest request);

}
