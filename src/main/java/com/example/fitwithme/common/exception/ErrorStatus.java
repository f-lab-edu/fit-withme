package com.example.fitwithme.common.exception;

public enum ErrorStatus {
    OK("Success"),
    NOT_FOUND_USER("등록되지 않은 아이디입니다."),
    WRONG_PASSWORD("비밀번호가 올바르지 않습니다."),
    DUPLICATE_ID("중복되는 아이디입니다."),
    CHECK_DUPLICATE_ID("아이디 중복 확인을 완료해주세요."),
    SIGNUP_FAIL("회원가입에 실패했습니다."),
    NOT_FOUND_LESSONLIST("해당 날짜에 수업이 없습니다."),
    NOT_FOUND_LESSON("수업 정보를 찾을 수 없습니다."),
    NOT_FOUND_RESERVEIST("예약한 수업이 없습니다."),
    CANCEL_FAIL("예약 취소에 실패했습니다."),
    LEAVE_FAIL("회원 탈퇴에 실패했습니다."),
    NOT_FOUND_SUBSCRIPTION("구매 가능한 이용권이 없습니다.");

    private final String message;

    ErrorStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
