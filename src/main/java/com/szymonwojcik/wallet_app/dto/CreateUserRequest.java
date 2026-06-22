package com.szymonwojcik.wallet_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String username;

    public CreateUserRequest(){}

}
