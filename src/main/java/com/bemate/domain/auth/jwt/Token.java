package com.bemate.domain.auth.jwt;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Token {
    private String accessToken;
    private LocalDateTime accessTokenExpireAt;
    private String refreshToken;
    private LocalDateTime refreshTokenExpireAt;
}
