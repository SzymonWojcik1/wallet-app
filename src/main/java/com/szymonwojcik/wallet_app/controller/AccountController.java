package com.szymonwojcik.wallet_app.controller;

import com.szymonwojcik.wallet_app.dto.CreateAccountRequest;
import com.szymonwojcik.wallet_app.dto.DepositRequest;
import com.szymonwojcik.wallet_app.dto.TransferRequest;
import com.szymonwojcik.wallet_app.dto.WithdrawRequest;
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
    public Account create(@RequestBody CreateAccountRequest request) {
        return accountService.create(request);
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @PostMapping("/{id}/deposit")
    public Account deposit(
            @PathVariable Long id,
            @RequestBody DepositRequest request) {
        return accountService.deposit(id, request.getAmount());
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(
            @PathVariable Long id,
            @RequestBody WithdrawRequest request) {
        return accountService.withdraw(id, request.getAmount());
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        accountService.transfer(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount());

        return "Transfer succesful";
    }
}
