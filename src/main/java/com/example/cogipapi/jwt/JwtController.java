package com.example.cogipapi.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class JwtController {

    // Secret key (you should store this securely)
    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @PostMapping("/data")
    public ResponseEntity<String> postData(@RequestBody Map<String, String> requestData) {
        // Retrieve user information from the JWT token
        String jwtToken = requestData.get("jwtToken");

        if (jwtToken != null) {
            String userData = decodeJWT(jwtToken);

            if (userData != null) {
                // Process and use the user data as needed
                String response = "Data received and processed: " + userData;
                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.badRequest().body("Invalid or missing JWT token");
    }

    @GetMapping("/generate-token")
    public ResponseEntity<String> generateToken() {
        // Create a JWT token with desired claims (e.g., user ID)
        String userId = "user123";
        String jwtToken = generateJWT(userId);

        return ResponseEntity.ok(jwtToken);
    }

    private String decodeJWT(String token) {
        try {
            String userId = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return userId;
        } catch (Exception e) {
            // Token is invalid or expired
            return null;
        }
    }

    private String generateJWT(String userId) {
        // Create a JWT token with your claims
        Date expirationDate = new Date(System.currentTimeMillis() + 10 * 60 * 1000); // 10 minutes in milliseconds
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userId);

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();

        return token;
    }
}
