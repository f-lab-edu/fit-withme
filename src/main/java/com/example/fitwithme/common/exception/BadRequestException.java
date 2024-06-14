package com.example.fitwithme.common.exception;

public class BadRequestException extends RuntimeException{
    private final ErrorStatus status;

    public BadRequestException(ErrorStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public ErrorStatus getStatus() {
        return this.status;
    }
}
