package com.example.fitwithme.presentation.dto.request;

import com.example.fitwithme.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String userId;

    private String userPassword;

    private String userName;

    private String email;

    private String phone;

    public User toDomain() {
        return new User(userId, userPassword, userName, email, phone);
    }
}

