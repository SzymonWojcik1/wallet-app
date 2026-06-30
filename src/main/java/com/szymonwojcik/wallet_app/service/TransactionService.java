package com.szymonwojcik.wallet_app.service;

import com.szymonwojcik.wallet_app.enums.TransactionStatus;
import com.szymonwojcik.wallet_app.enums.TransactionType;
import com.szymonwojcik.wallet_app.model.Account;
import com.szymonwojcik.wallet_app.model.Transaction;
import com.szymonwojcik.wallet_app.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction){
        return  transactionRepository.save(transaction);
    }

    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(
            TransactionType type,
            TransactionStatus status,
            BigDecimal amount,
            Account fromAccount,
            Account toAccount) {
        Transaction transaction = new Transaction();

        transaction.setType(type);
        transaction.setStatus(status);
        transaction.setAmount(amount);
        transaction.setCreatedAt(LocalDateTime.now());

        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getByAccount(Long accountId) {
        return transactionRepository.findByFromAccountIdOrToAccountId(
                accountId,
                accountId
        );
    }
}
