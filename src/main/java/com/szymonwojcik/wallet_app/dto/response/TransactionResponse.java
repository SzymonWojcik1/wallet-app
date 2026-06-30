package com.szymonwojcik.wallet_app.dto.response;

import com.szymonwojcik.wallet_app.enums.TransactionStatus;
import com.szymonwojcik.wallet_app.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private LocalDateTime createdAt;
    private Long fromAccountId;
    private Long toAccountId;

    private String message;
}
