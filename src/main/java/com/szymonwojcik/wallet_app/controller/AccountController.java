package com.szymonwojcik.wallet_app.controller;

import com.szymonwojcik.wallet_app.dto.request.CreateAccountRequest;
import com.szymonwojcik.wallet_app.dto.request.DepositRequest;
import com.szymonwojcik.wallet_app.dto.request.TransferRequest;
import com.szymonwojcik.wallet_app.dto.request.WithdrawRequest;
import com.szymonwojcik.wallet_app.dto.response.AccountResponse;
import com.szymonwojcik.wallet_app.dto.response.TransactionResponse;
import com.szymonwojcik.wallet_app.mapper.AccountMapper;
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
    public AccountResponse create(@RequestBody CreateAccountRequest request) {
        return AccountMapper.toResponse(accountService.create(request));
    }

    @GetMapping
    public List<AccountResponse> getAll() {
        return accountService.getAll()
                .stream()
                .map(AccountMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable Long id) {
        return AccountMapper.toResponse(accountService.getById(id));
    }

    @PostMapping("/{id}/deposit")
    public AccountResponse deposit(
            @PathVariable Long id,
            @RequestBody DepositRequest request) {
        return AccountMapper.toResponse(accountService.deposit(id, request.getAmount()));
    }

    @PostMapping("/{id}/withdraw")
    public AccountResponse withdraw(
            @PathVariable Long id,
            @RequestBody WithdrawRequest request) {
        return AccountMapper.toResponse(accountService.withdraw(id, request.getAmount()));
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        accountService.transfer(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount());

        return "Transfer to account: "+request.getToAccountId()+" of "+request.getAmount()+" succesful";
    }
}
