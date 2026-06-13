package com.szymonwojcik.wallet_app.service;

import com.szymonwojcik.wallet_app.model.Account;
import com.szymonwojcik.wallet_app.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Account account){
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getById(Long id){
        return  accountRepository.findById(id).orElseThrow();
    }
}
