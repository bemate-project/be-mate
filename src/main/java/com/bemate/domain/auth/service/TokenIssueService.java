package com.bemate.domain.auth.service;

import com.bemate.domain.auth.jwt.Token;
import com.bemate.domain.auth.jwt.JwtTokenProvider;
import com.bemate.domain.auth.jwt.TokenType;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Service
public class TokenIssueService {

    private static final int ACCESS_TOKEN_DURATION_SECONDS = 60 * 30;
    private static final int REFRESH_TOKEN_DURATION_SECONDS = 60 * 60 * 24 * 14;

    public Token getTokensOfUser(Long userNo) {
        var now = Instant.now();
        var expiryDateOfAccessToken = now.plusSeconds(ACCESS_TOKEN_DURATION_SECONDS);
        var expiryDateOfRefreshToken = now.plusSeconds(REFRESH_TOKEN_DURATION_SECONDS);

        var accessTokenPayload = Map.of(
                "userNo", userNo,
                "type", TokenType.ACCESS.name(),
                "iat", now.getEpochSecond(),
                "exp", expiryDateOfAccessToken.getEpochSecond()
        );
        var accessToken = JwtTokenProvider.generateToken(accessTokenPayload);

        var refreshTokenPayload = Map.of(
                "userNo", userNo,
                "type", TokenType.REFRESH.name(),
                "iat", now.getEpochSecond(),
                "exp", expiryDateOfRefreshToken.getEpochSecond()
        );
        var refreshToken = JwtTokenProvider.generateToken(refreshTokenPayload);

        return Token.builder()
                .accessToken(accessToken)
                .accessTokenExpireAt(
                        LocalDateTime.ofInstant(expiryDateOfAccessToken, ZoneId.of("Asia/Seoul")))
                .refreshToken(refreshToken)
                .refreshTokenExpireAt(
                        LocalDateTime.ofInstant(expiryDateOfRefreshToken, ZoneId.of("Asia/Seoul")))
                .build();
    }
}
