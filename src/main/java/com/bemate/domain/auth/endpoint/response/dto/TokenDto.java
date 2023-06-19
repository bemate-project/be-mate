package com.bemate.domain.auth.endpoint.response.dto;

import com.bemate.domain.auth.jwt.Token;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TokenDto {
    private String accessToken;
    private LocalDateTime accessTokenExpireAt;
    private String refreshToken;
    private LocalDateTime refreshTokenExpireAt;

    public static TokenDto from(Token token) {
        return TokenDto.builder()
                .accessToken(token.getAccessToken())
                .accessTokenExpireAt(token.getAccessTokenExpireAt())
                .refreshToken(token.getRefreshToken())
                .refreshTokenExpireAt(token.getRefreshTokenExpireAt())
                .build();
    }
}
