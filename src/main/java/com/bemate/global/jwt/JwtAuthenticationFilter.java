package com.bemate.global.jwt;

import com.bemate.global.exception.TokenTypeMismatchException;
import com.bemate.global.exception.handler.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.bemate.global.jwt.JwtTokenProvider.parseTokenType;
import static com.bemate.global.jwt.JwtTokenProvider.validateToken;
import static org.springframework.util.StringUtils.hasText;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            trySettingAuthentication(request);
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
    }

    private void trySettingAuthentication(HttpServletRequest request) {
        var jwt = getJwtFromRequest(request);

        if(!hasText(jwt) || !validateToken(jwt)) {
            return;
        }

        if(!isAccessToken(jwt)) {
            throw TokenTypeMismatchException.accessTokenExpected(jwt);
        }


    }

    private static boolean isAccessToken(String jwt) {
        return parseTokenType(jwt).equals(TokenType.ACCESS.name());
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");
        if (hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}
