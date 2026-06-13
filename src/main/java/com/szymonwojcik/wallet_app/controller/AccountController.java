package com.szymonwojcik.wallet_app.controller;

import com.szymonwojcik.wallet_app.model.Account;
import com.szymonwojcik.wallet_app.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account create(@RequestBody Account account){
        return accountService.create(account);
    }

    @GetMapping
    public List<Account> getAll(){
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id){
        return accountService.getById(id);
    }
}
