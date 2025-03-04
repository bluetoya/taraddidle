package com.bluetoya.taradiddle.common.security;

import com.bluetoya.taradiddle.common.util.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.function.Function;

@Component
public class JwtProvider {

    @Value("${jwt.access-token.secret-key}")
    private String accessKey;

    @Value("${jwt.access-token.expiration}")
    private Integer accessExpiration;

    @Value("${jwt.refresh-token.secret-key}")
    private String refreshKey;

    @Value("${jwt.refresh-token.expiration}")
    private Integer refreshExpiration;

    private SecretKey generateAccessTokenKey() {
        return Keys.hmacShaKeyFor(accessKey.getBytes());
    }

    private SecretKey generateRefreshTokenKey() {
        return Keys.hmacShaKeyFor(refreshKey.getBytes());
    }

    public String generateAccessToken(String userId) {
        return Jwts.builder()
            .subject(userId)
            .issuedAt(DateUtil.nowAsDate())
            .expiration(DateUtil.getExpiration(accessExpiration))
            .signWith(this.generateAccessTokenKey())
            .compact();
    }

    public String generateRefreshToken(String userId) {
        return Jwts.builder()
            .subject(userId)
            .issuedAt(DateUtil.nowAsDate())
            .expiration(DateUtil.getExpiration(refreshExpiration))
            .signWith(this.generateRefreshTokenKey())
            .compact();
    }

    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        if (!isTokenValid(token)) {
            return null;
        }

        final Claims claims = getClaimFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getClaimFromToken(String token) {
        return Jwts.parser().verifyWith(generateAccessTokenKey()).build().parseSignedClaims(token)
            .getPayload();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith(generateAccessTokenKey()).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
