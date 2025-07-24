package io.youth.home.domain.auth.entity;

import lombok.Getter;

@Getter
public enum TokenType {
    ACCESS("access"),
    REFRESH("refresh");

    private final String value;
    TokenType(String value) { this.value = value; }
}

