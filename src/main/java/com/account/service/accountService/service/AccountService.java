package com.account.service.accountService.service;

import java.util.List;
import java.util.Optional;

import com.account.service.accountService.entiry.Account;

public interface AccountService {
	
	public void add(Account account);
	
	public List<Account>getAllAccount(); 
	
	public Optional<Account> getAccountById(int accountId);
	
	public Account depositeValueToAccountAvailable(int accountId,Account account);
	
	public Account debitValueToAccountAvailable(int accountId,Account account);

}
