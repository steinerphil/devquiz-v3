package de.neuefische.devquiz.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTUtilsService {

    @Value("${neuefische.devquiz.jwt.secret}")
    private String JWT_SECRET;

    public long createDuration(){
        return 4 * 60 * 60 * 1000;
    }

    public String createToken(HashMap<String, Object> claims, String subject) {

        //generate JWT
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject) //username
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(Duration.ofMillis(createDuration()))))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    public String extractUsername(String token){
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
