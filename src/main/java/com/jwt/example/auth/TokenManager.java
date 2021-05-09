package com.jwt.example.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TokenManager {

  private static final int VALIDITY = 5 * 60 * 1000;
  private static final long CURRENT_TIME_MILLIS = System.currentTimeMillis();
  private static final Key KEY_VAL=Keys.secretKeyFor(SignatureAlgorithm.HS256);

  String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuer("seydabenli")
        .setIssuedAt(new Date(CURRENT_TIME_MILLIS))
        .setExpiration(new Date(CURRENT_TIME_MILLIS + VALIDITY))
        .signWith(KEY_VAL)
        .compact();

  }

  public boolean tokenValidate(String token) {
    if (getUserNameToken(token) != null && isExpired(token)) {
      return true;
    } else {
      return false;
    }
  }

  public String getUserNameToken(String token) {
    Claims claims = getClaims(token);
    return claims.getSubject();
  }

  public boolean isExpired(String token) {
    Claims claims = getClaims(token);
    return claims.getExpiration().after(new Date(CURRENT_TIME_MILLIS));
  }

  private Claims getClaims(String token) {
    return Jwts.parser().setSigningKey(KEY_VAL).parseClaimsJws(token).getBody();
  }

}
