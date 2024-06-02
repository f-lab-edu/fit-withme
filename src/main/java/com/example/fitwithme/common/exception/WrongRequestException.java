package com.example.fitwithme.common.exception;

public class WrongRequestException extends RuntimeException{
    private final ErrorStatus status;

    public WrongRequestException(ErrorStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public ErrorStatus getStatus() {
        return this.status;
    }
}
