package io.youth.home.domain.auth.dto;

public record LoginRequest(
        String email,
        String password
) {}

