package com.bluetoya.taradiddle.common.config;

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

  @Value("${jwt.secret-key}")
  private String accessKey;

  @Value("${jwt.expiration}")
  private Integer expiration;

  private SecretKey generateKey() {
    return Keys.hmacShaKeyFor(accessKey.getBytes());
  }

  public String generateAccessToken(String userId) {
    return Jwts.builder()
            .subject(userId)
            .issuedAt(DateUtil.nowAsDate())
            .expiration(DateUtil.getExpiration(expiration))
            .signWith(this.generateKey())
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
    return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}
