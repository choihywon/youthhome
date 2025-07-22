package io.youth.home.domain.auth.dto;

import jakarta.servlet.http.Cookie;

public record ReissueResponse (
        String newAccessToken,
        Cookie refreshCookie
){}
