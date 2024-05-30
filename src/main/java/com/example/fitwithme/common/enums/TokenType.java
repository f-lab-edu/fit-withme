package com.example.fitwithme.common.enums;

public enum TokenType {
    ACCESS_TOKEN("ACCESS_TOKEN"),    // 프로젝트 기본정보 관리
    REFRESH_TOKEN("REFRESH_TOKEN");       // Task 수행 정보관리

    TokenType(String tokenType) { this.tokenType = tokenType; }

    private final String tokenType;

    public String getTokenType() { return tokenType; }

    @Override
    public String toString() {
        return this.tokenType;
    }

}
