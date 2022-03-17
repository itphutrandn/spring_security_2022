package com.eazybytes.config;

import com.eazybytes.models.BankUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY  = 30000000;

    public static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //generate token for user
    public String generateTokenByUsername(UserDetails userDetails) {
        return generateToken(((BankUserDetail) userDetails).getUser().getEmail());
    }

    public String generateToken(String username) {
        String jws = Jwts.builder()
                .setSubject("Bank").signWith(key)
                .setIssuer(username)
                .setExpiration(new Date(new Date().getTime() + JWT_TOKEN_VALIDITY))
                .setNotBefore(new Date())
                .setIssuedAt(new Date())
                .compact();
        return jws;
    }

    public boolean verify(String token, String username) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getIssuer().equals(username);
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getIssuer();
    }

    //validate token
    public boolean validateToken(String token, BankUserDetail userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUser().getEmail()) && !isTokenExpired(token));
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
