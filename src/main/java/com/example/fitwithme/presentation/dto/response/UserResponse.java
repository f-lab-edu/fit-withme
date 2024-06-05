package com.example.fitwithme.presentation.dto.response;

import com.example.fitwithme.domain.model.User;
import lombok.Builder;
import lombok.Getter;


public class UserResponse {
    @Getter
    @Builder
    public static class tokenInfo {
        private String grantType;
        private String accessToken;
        private String refreshToken;
    }

}
