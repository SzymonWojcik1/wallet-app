package com.szymonwojcik.wallet_app.controller;

import com.szymonwojcik.wallet_app.dto.response.TransactionResponse;
import com.szymonwojcik.wallet_app.mapper.TransactionMapper;
import com.szymonwojcik.wallet_app.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionResponse> getAll(){ return transactionService.getAll()
            .stream()
            .map(TransactionMapper::toResponse)
            .toList();
    }

    @GetMapping("/account/{accountId}")
    public List<TransactionResponse> getByAccount(@PathVariable Long accountId) {
        return transactionService.getByAccount(accountId)
                .stream()
                .map(TransactionMapper::toResponse)
                .toList();
    }
}
