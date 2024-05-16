package com.example.fitwithme.common.exception;

public class NotFoundException extends RuntimeException{
    private final ErrorStatus status;

    public NotFoundException(ErrorStatus status) {
        this.status = status;
    }

    public ErrorStatus getStatus() {
        return this.status;
    }
}
