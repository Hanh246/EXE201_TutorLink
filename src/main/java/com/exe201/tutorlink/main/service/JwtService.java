package com.exe201.tutorlink.main.service;

import com.exe201.tutorlink.main.entity.Users;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${app.jwt.secret}")
    private String secretKey;
    @Value("${app.jwt.expiration}")
    private long jwtExpiration;
    @Value("${app.jwt.refreshExpiration}")
    private long refreshExpiration;

    public String generateToken(Users user) {
        return buildToken(user, jwtExpiration);
    }

    public String generateRefreshToken(Users user) {
        return buildToken(user, refreshExpiration);
    }

    private String buildToken(Users user, long expiration) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
