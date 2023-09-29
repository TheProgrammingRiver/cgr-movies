package com.example.cgrmovies.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());
    @Value("${jwt-secret}")
    public String jwtSecret;

    @Value("${jwt-expiration-ms}")
    public int jwtExpMS;

    /**
     * Generates a jwt token based on provided user details
     * @param myUserDetails
     * @return jwt token
     */
    public String generateJwtToken(MyUserDetails myUserDetails){
        return Jwts.builder()
                .setSubject(myUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpMS))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    /**
     * Gets a user from the jwt token
     * @param token
     * @return username
     */
    public String getUsernameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(jwtSecret)
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validates the jwt token
     * @param authToken
     * @return true if token is valid or false if not
     */
    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.log(Level.SEVERE, "Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.log(Level.SEVERE, "JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.log(Level.SEVERE, "JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "JWT claims string is empty: {}", e.getMessage());
        }
        return false;

    }

}
