package com.prt.storeapi.util;

import com.prt.storeapi.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static String secret= "store-api-secret";
    private static long expiryDuration = 60*60*24;

    public String generateToken(User user){

        long mills = System.currentTimeMillis();
        long expiration = mills + expiryDuration * 1000;
        Date date= new Date(mills);
        Date exp = new Date(expiration);

        Claims claims= Jwts.claims()
                            .setIssuer(String.valueOf(user.getId()))
                            .setIssuedAt(date)
                            .setExpiration(exp);

        claims.put("name", user.getFirstName()+" "+user.getLastName());
        claims.put("email", user.getEmail());
        claims.put("id", user.getId());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims verify (String auth) throws Exception {

        try {
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(auth).getBody();
                return claims;
        } catch (Exception e) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}
