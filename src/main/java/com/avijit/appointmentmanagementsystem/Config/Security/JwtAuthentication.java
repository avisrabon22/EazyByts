package com.avijit.appointmentmanagementsystem.Config.Security;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthentication  {



private static  final Key secret_key= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String email) throws IOException {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 3600000);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(secret_key)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secret_key)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    // Extract subject (username) from JWT token

    public static String extractSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret_key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


}
