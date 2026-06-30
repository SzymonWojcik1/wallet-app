package com.szymonwojcik.wallet_app.mapper;

import com.szymonwojcik.wallet_app.dto.response.TransactionResponse;
import com.szymonwojcik.wallet_app.model.Transaction;

public class TransactionMapper {
    public static TransactionResponse toResponse(Transaction transaction){
        TransactionResponse response = new TransactionResponse();

        response.setId(transaction.getId());
        response.setAmount(transaction.getAmount());
        response.setType(transaction.getType());
        response.setStatus(transaction.getStatus());
        response.setCreatedAt(transaction.getCreatedAt());

        if (transaction.getFromAccount()!=null) {
            response.setFromAccountId(transaction.getFromAccount().getId());
        }
        if (transaction.getToAccount()!=null) {
            response.setToAccountId(transaction.getToAccount().getId());
        }
        return response;
    }
}
