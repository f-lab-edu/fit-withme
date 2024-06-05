package com.example.fitwithme.common.exception;

public enum ErrorStatus {
    OK("Success"),
    NOT_FOUND_USER("등록되지 않은 아이디입니다."),
    WRONG_PASSWORD("비밀번호가 올바르지 않습니다."),
    DUPLICATE_ID("중복되는 아이디입니다."),
    SIGNUP_FAIL("회원가입에 실패했습니다.");

    private final String message;

    ErrorStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
