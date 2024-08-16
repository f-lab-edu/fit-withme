package com.example.fitwithme.common.exception;

public enum S3ErrorStatus {
    EMPTY_FILE_EXCEPTION("첨부된 이미지가 없습니다."),
    IO_EXCEPTION_ON_IMAGE_UPLOAD("IOException이 발생했습니다."),
    NO_FILE_EXTENSION("잘못된 파일 형식입니다."),
    INVALID_FILE_EXTENSION("이미지 파일 형식이 아닙니다."),
    PUT_OBJECT_EXCEPTION("이미지 업로드에 실패했습니다."),
    IO_EXCEPTION_ON_IMAGE_DELETE("이미지 삭제에 실패했습니다.");

    private final String message;

    S3ErrorStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
