package com.example.fitwithme.presentation.dto.response;

import com.example.fitwithme.domain.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private String grantType;
    private String accessToken;
    private String refreshToken;

}

