package com.szymonwojcik.wallet_app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponse {

    private Long id;
    private BigDecimal balance;
    private Long userId;
}
