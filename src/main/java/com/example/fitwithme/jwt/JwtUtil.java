package com.example.fitwithme.jwt;

import com.example.fitwithme.presentation.dto.response.UserResponse;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private String secretKey;
    private long accessTokenValidity;
    private long refreshTokenValidity;

    public JwtUtil(@Value("${jwt.secret}") String secretKey, @Value("${jwt.accessTokenValidity}") long accessTokenValidity, @Value("${jwt.refreshTokenValidity}")long refreshTokenValidity) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public UserResponse generateTokens(String userId) {
        String accessToken = createAccessToken(userId);
        String refreshToken = createRefreshToken(userId);

        UserResponse tokenInfo = UserResponse.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return tokenInfo;
    }

    private String createAccessToken(String userId) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("type", "Access");

        return Jwts.builder()
                .setClaims(claims)
                .setSubject("AccessToken")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String createRefreshToken(String userId) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("type", "Refresh");

        return Jwts.builder()
                .setClaims(claims)
                .setSubject("RefreshToken")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String refreshAccessToken(String refreshToken) {
        if (validateToken(refreshToken)) {
            Claims claims = getClaimsFromToken(refreshToken);
            String userId = claims.get("userId", String.class);
            return createAccessToken(userId);
        }
        return null;
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
