package com.example.fitwithme.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;


public class LessonResponse {
    @Getter
    @Builder
    public static class tokenInfo {
        private String grantType;
        private String accessToken;
        private String refreshToken;
    }

}
