package com.szymonwojcik.wallet_app.service;

import com.szymonwojcik.wallet_app.dto.request.CreateAccountRequest;
import com.szymonwojcik.wallet_app.enums.TransactionStatus;
import com.szymonwojcik.wallet_app.enums.TransactionType;
import com.szymonwojcik.wallet_app.model.Account;
import com.szymonwojcik.wallet_app.model.User;
import com.szymonwojcik.wallet_app.repository.AccountRepository;
import com.szymonwojcik.wallet_app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionService transactionService;

    public Account deposit(Long accountId, BigDecimal amount) {
        Account account = getById(accountId);

        account.setBalance(account.getBalance().add(amount));

        Account saveAccount = accountRepository.save(account);

        transactionService.createTransaction(
                TransactionType.DEPOSIT,
                TransactionStatus.COMPLETED,
                amount,
                null,
                saveAccount
        );

        return saveAccount;
    }

    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = getById(accountId);

        checkIfEnoughFunds(account, amount);

        account.setBalance(account.getBalance().subtract(amount));

        Account saveAccount = accountRepository.save(account);

        transactionService.createTransaction(
                TransactionType.WITHDRAW,
                TransactionStatus.COMPLETED,
                amount,
                saveAccount,
                null
        );

        return saveAccount;
    }

    @Transactional
    public void transfer(Long fromAccountId,
                         Long toAccountId,
                         BigDecimal amount) {
        Account from = getById(fromAccountId);
        Account to = getById(toAccountId);

        checkIfEnoughFunds(from, amount);

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);

        transactionService.createTransaction(
                TransactionType.TRANSFER,
                TransactionStatus.COMPLETED,
                amount,
                from,
                to
        );
    }

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionService = transactionService;
    }

    public Account create(CreateAccountRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found"));
        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);

        return accountRepository.save(account);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getById(Long id){
        return  accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account not found"));
    }

    private void checkIfEnoughFunds(Account acc, BigDecimal am){
        if(acc.getBalance().compareTo(am)<0) {
            throw new RuntimeException("Insufficient funds");
        }
    }
}
