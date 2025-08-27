package com.courierx.tracking.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
//	private static final String SECRET = "courierx-super-secret-key-32bytes-minimum!!";

    private final Key key;
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    public String extractUsername(String token) {
    	return Jwts.parserBuilder().setSigningKey(key).build()
    			.parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean isValid(String token) {
    	try {
    		Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    		return true;
    	} catch(JwtException | IllegalArgumentException e) {
    		return false;
    	}
    }
    
}
