package com.szymonwojcik.wallet_app.mapper;

import com.szymonwojcik.wallet_app.dto.response.UserResponse;
import com.szymonwojcik.wallet_app.model.User;

public class UserMapper {
    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());

        return response;
    }
}
