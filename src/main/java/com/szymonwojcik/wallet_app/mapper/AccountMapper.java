package com.szymonwojcik.wallet_app.mapper;

import com.szymonwojcik.wallet_app.dto.response.AccountResponse;
import com.szymonwojcik.wallet_app.model.Account;

public class AccountMapper {
    public static AccountResponse toResponse(Account account) {
        AccountResponse response = new AccountResponse();

        response.setId(account.getId());
        response.setBalance(account.getBalance());

        if (account.getUser() != null) {
            response.setUserId(account.getUser().getId());
        }

        return  response;
    }
}
