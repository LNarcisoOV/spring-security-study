package com.security.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.security.model.User;

@Service
public class TokenService {
    
    private static final String BRAZILLIAN_TIME_ZONE = "-03:00";
    
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT
                    .create()
                    .withIssuer("spring-security-study")
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        }catch(JWTCreationException e) {
            throw new RuntimeException("Error while generate token");
        }
    }
    
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT
                    .require(algorithm)
                    .withIssuer("spring-security-study")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException e) {
            return "";
        }
    }
    
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of(BRAZILLIAN_TIME_ZONE));
    }
}
