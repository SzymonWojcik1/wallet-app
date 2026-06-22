package com.szymonwojcik.wallet_app.service;

import com.szymonwojcik.wallet_app.dto.CreateAccountRequest;
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

    public Account deposit(Long accountId, BigDecimal amount) {
        Account account = getById(accountId);

        account.setBalance(account.getBalance().add(amount));

        return accountRepository.save(account);

    }

    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = getById(accountId);

        checkIfEnoughFunds(account, amount);

        account.setBalance(account.getBalance().subtract(amount));

        return accountRepository.save(account);
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

    }

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
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
