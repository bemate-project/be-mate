package com.bemate.domain.auth.jwt;

import com.bemate.domain.user.UserAuthentication;
import com.bemate.global.exception.TokenTypeMismatchException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.bemate.domain.auth.jwt.JwtTokenProvider.*;
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

        filterChain.doFilter(request, response);
    }

    private void trySettingAuthentication(HttpServletRequest request) {
        var jwt = getJwtFromRequest(request);

        if(!hasText(jwt) || !validateToken(jwt)) {
            return;
        }

        if(!isAccessToken(jwt)) {
            throw TokenTypeMismatchException.accessTokenExpected(jwt);
        }

        var authentication = new UserAuthentication(parseUserNo(jwt), parseRole(jwt));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
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
