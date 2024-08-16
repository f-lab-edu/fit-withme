package com.example.fitwithme.common.exception;

public class S3Exception extends RuntimeException{
    private final S3ErrorStatus status;

    public S3Exception(S3ErrorStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public S3ErrorStatus getStatus() {
        return this.status;
    }
}
