package com.szymonwojcik.wallet_app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAccountRequest {
    private Long userId;

    public CreateAccountRequest() {
    }
}
