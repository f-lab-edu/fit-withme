package com.example.fitwithme.common.enums;

public enum TokenType {
    ACCESS_TOKEN("ACCESS_TOKEN"),
    REFRESH_TOKEN("REFRESH_TOKEN");

    TokenType(String tokenType) { this.tokenType = tokenType; }

    private final String tokenType;

    public String getTokenType() { return tokenType; }

    @Override
    public String toString() {
        return this.tokenType;
    }

}