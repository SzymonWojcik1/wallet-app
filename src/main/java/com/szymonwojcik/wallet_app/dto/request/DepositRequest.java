package com.szymonwojcik.wallet_app.dto.request;

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
