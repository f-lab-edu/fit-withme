package com.example.fitwithme.common.exception;

public class NotFoundException extends RuntimeException{
    private final ErrorStatus status;

    public NotFoundException(ErrorStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public ErrorStatus getStatus() {
        return this.status;
    }
}
