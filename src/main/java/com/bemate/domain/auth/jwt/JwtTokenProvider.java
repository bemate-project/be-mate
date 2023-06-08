package com.bemate.domain.auth.jwt;

import com.bemate.domain.auth.UserDetailsServiceImpl;
import com.bemate.domain.user.Role;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private static String secretKey;

    public static String generateToken(Map<String, Object> payload) {
        return Jwts.builder()
                .setClaims(payload)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static String parseTokenType(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("type", String.class);
    }

    public static Long parseUserNo(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("userNo", Long.class);
    }

    public static Role parseRole(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("role", Role.class);
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log .error("Invalid JWT signature", ex);
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token", ex);
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token", ex);
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token", ex);
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.", ex);
        }
        return false;
    }
}
