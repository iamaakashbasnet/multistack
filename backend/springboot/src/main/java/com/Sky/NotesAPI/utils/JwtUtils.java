package com.Sky.NotesAPI.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;


@Component
public class JwtUtils {

    @Value("django-insecure-hgb_kwmtl#wiyuk&23fzqt$*fk_dtt1oqc$e8=)3b$y@alqaap")
    private String jwtSecret;

    private Claims extractAllClaims(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);  // remove "Bearer "
        }

        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("user_id", Long.class);
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("username", String.class);
    }
}