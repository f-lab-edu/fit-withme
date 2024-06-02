package com.example.fitwithme.presentation.dto.request;

import com.example.fitwithme.domain.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {

    @Getter
    @AllArgsConstructor
    public static class login {

        @NotBlank(message = "아이디를 입력해주세요.")
        private String userId;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String userPassword;

    }
}
