package com.example.fitwithme.presentation.dto.request;

import com.example.fitwithme.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


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

    @Getter
    @AllArgsConstructor
    public static class signUp {

        @NotBlank(message = "이름을 입력해주세요.")
        private String userName;

        @NotBlank(message = "아이디를 입력해주세요.")
        @Pattern (regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영어와 숫자의 조합으로만 가능합니다.")
        private String userId;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8 ~ 20자의 비밀번호여야 합니다.")
        private String userPassword;

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "휴대전화를 입력해주세요.")
        @Pattern (regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "휴대전화 형식이 아닙니다.")
        private String phone;

        public User toDomain() {
            return User.builder()
                    .userName(this.userName)
                    .userId(this.userId)
                    .userPassword(this.userPassword)
                    .email(this.email)
                    .phone(this.phone)
                    .build();
        }

    }

}
