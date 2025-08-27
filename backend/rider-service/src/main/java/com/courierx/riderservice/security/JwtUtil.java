package com.courierx.riderservice.security;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
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
