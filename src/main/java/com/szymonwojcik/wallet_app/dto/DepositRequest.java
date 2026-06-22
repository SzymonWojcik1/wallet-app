package com.szymonwojcik.wallet_app.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositRequest {
    private BigDecimal amount;

    public DepositRequest() {
    }
}
