package com.roehr.equipmenttracker.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TokenService {

    private final String secretKey = "12fbe6819af54d0473113f11d7feadbe82b5a544ebd62094b2d9dfa77fd27cd0";
    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    public String createToken(String username) {
        logger.info("Creating token for user: {}", username);
        Date now = new Date();
        long validityInMilliseconds = 3600000; // 1 hour
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        logger.info("Token created for user: {}", username);
        return token;
    }

    public boolean validateToken(String token) {
        try {
            logger.info("Validating token...");
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            logger.info("Token is valid.");
            return true;
        } catch (Exception e) {
            logger.error("Token validation error: {}", e.getMessage());
            return false;
        }
    }


    // private Boolean isTokenExpired(String token) {
    //     final Date expiration = getExpirationDateFromToken(token);
    //     return expiration.before(new Date());
    // }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
