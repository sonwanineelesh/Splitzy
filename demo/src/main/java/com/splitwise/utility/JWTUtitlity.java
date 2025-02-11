package com.splitwise.utility;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtitlity {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateJwtToken(String username) {
    	return Jwts.builder()
    	           .setSubject(username)
    	           .setIssuedAt(new Date())
    	           .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
    	           .signWith(getSigningKey(), SignatureAlgorithm.HS256)
    	           .compact();

    }
}

