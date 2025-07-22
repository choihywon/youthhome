package io.youth.home.domain.auth.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
