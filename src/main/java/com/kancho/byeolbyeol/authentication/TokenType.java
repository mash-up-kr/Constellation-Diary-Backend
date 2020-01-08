package com.kancho.byeolbyeol.authentication;

import lombok.Getter;

@Getter
public enum TokenType {
    AUTHENTICATION_TOKEN("Authentication Token"),
    REFRESH_TOKEN("Refresh Token"),
    REGISTER_TOKEN("Refresh Token");

    private String value;

    TokenType(String value) {
        this.value = value;
    }

    public boolean verifyValue(String value) {
        return this.value.equals(value);
    }
}
