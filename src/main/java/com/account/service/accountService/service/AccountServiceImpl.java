package com.account.service.accountService.service;

import java.util.List;
import java.util.Optional;

import com.account.service.accountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.account.service.accountService.entiry.Account;
import com.account.service.accountService.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	KafkaTemplate kafkaTemplte;
	

	public void add(Account account) {
		accountRepo.save(account);
	}

	public List<Account> getAllAccount() {
	 return accountRepo.findAll();
	}

	public Optional<Account> getAccountById(int accountId) {
		return accountRepo.findById(accountId);
    
	}

	@Override
	public synchronized Account depositeValueToAccountAvailable(int accountId, Account updatedAccount) {
		Optional<Account>existingAccount = accountRepo.findById(accountId);
		
		if(existingAccount.isPresent()) {
			
			Account existingAcc = existingAccount.get();
			int existingBalance = existingAcc.getBalance();
			existingAcc.setAccountHolderName(updatedAccount.getAccountHolderName());
			existingAcc.setBalance(existingBalance + updatedAccount.getTransferedAmount());
			existingAcc.setAccountFromId(updatedAccount.getAccountFromId());
			existingAcc.setAccountToId(updatedAccount.getAccountToId());
			existingAcc.setTransferedAmount(updatedAccount.getTransferedAmount());
						
			Account newAccount = accountRepo.save(existingAcc);
			
			//from here we can call the notification service
			
			kafkaTemplte.send("diposite-event-topic",newAccount);
				
			return newAccount;
		}
		
		return new Account();
	}
	
	
	@Override
	public synchronized Account debitValueToAccountAvailable(int accountId, Account updatedAccount) {
		Optional<Account>existingAccount = accountRepo.findById(accountId);
		
		if(existingAccount.isPresent()) {
			
			Account existingAcc = existingAccount.get();
			int existingBalance = existingAcc.getBalance();
			existingAcc.setAccountHolderName(updatedAccount.getAccountHolderName());
			
			if(existingBalance>updatedAccount.getTransferedAmount()) {
				existingAcc.setBalance(existingBalance - updatedAccount.getTransferedAmount());
			}else {
				kafkaTemplte.send("amount-not-sufficient-topic",existingAcc);
			}
			
			existingAcc.setAccountFromId(updatedAccount.getAccountFromId());
			existingAcc.setAccountToId(updatedAccount.getAccountToId());
			existingAcc.setTransferedAmount(updatedAccount.getTransferedAmount());
						
			Account newAccount = accountRepo.save(existingAcc);
			
			//from here we can call the notification kafka consumer com.account.service.accountService.consumer.AccountActivityNotificationConsumer
			
			kafkaTemplte.send("debited-event-topic",newAccount);
				
			return newAccount;
		}
		
		return new Account();
	}
}
