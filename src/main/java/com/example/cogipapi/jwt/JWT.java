package com.example.cogipapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;


public class JWT {

    // Secret key (you should store this securely)
    private static final byte[] secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

    public String encodeJWT(String userId, String dataKey, String dataValue) {
        // Calculate the expiration time (10 minutes from now)
        Date expirationDate = new Date(System.currentTimeMillis() + 10 * 60 * 1000); // 10 minutes in milliseconds

        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(expirationDate)
                .claim(dataKey, dataValue) // Add custom data as a claim
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public String decodeJWT(String token, String dataKey) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Check the expiration
            Date expirationDate = claims.getExpiration();
            Date now = new Date();
            if (expirationDate.before(now)) {
                // Token has expired
                return null;
            }

            // Retrieve custom data using the dataKey
            String dataValue = claims.get(dataKey, String.class);

            return dataValue;
        } catch (Exception e) {
            // Token is invalid or expired
            return null;
        }
    }
}