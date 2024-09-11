package com.account.service.accountService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.service.accountService.entiry.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

}
