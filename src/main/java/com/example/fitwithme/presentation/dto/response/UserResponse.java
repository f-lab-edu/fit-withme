package com.example.fitwithme.presentation.dto.response;

import com.example.fitwithme.domain.model.User;

public record UserResponse(String userId, String userPassword, String userName, String email, String phone) {

    public static UserResponse from(User user) {
        return new UserResponse(user.userId(), user.userPassword(), user.userName(), user.email(), user.phone());
    }
}

