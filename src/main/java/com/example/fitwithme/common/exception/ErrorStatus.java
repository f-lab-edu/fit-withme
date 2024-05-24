package com.example.fitwithme.common.exception;

public enum ErrorStatus {
    OK("Success"),
    NOT_INPUT("아이디와 비밀번호를 모두 입력해 주세요."),
    NOT_FOUND_USER("등록되지 않은 아이디입니다."),
    WRONG_PASSWORD("비밀번호가 올바르지 않습니다.");

    private final String message;

    ErrorStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
