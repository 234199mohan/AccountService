package com.account.service.accountService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.account.service.accountService.service.AccountService;
import com.account.service.accountService.entiry.Account;

import java.util.Optional;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	@PostMapping("/account/save")
	public void addNewAccount(@RequestBody Account account) {
		accountService.add(account);
	}

	@GetMapping("/account/{id}")
	public Optional<Account> getAccountById(@PathVariable int id) {
		return accountService.getAccountById(id);
	}

	@PostMapping("/account/{id}")
	public Account depositeValueToAccountAvailable(@PathVariable int id, @RequestBody Account account) {
		return accountService.depositeValueToAccountAvailable(id, account);

	}
	
	
	@PostMapping("/account/{id}")
	public Account debitValueToAccountAvailable(@PathVariable int id, @RequestBody Account account) {
		return accountService.debitValueToAccountAvailable(id, account);

	}

}
