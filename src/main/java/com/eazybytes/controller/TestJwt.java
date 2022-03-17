package com.eazybytes.controller;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

public class TestJwt {
    public static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static void main(String[] args) {
        String jws = Jwts.builder()
                .setSubject("Bank").signWith(key)
                .setIssuer("itphutran.com@gmail.com")
                .setExpiration(new Date(new Date().getTime() + 10000))
                .setNotBefore(new Date())
                .setIssuedAt(new Date())
                .compact();
        System.out.println(jws);
        System.out.println("Verify:"+verify(jws, "itphutran.com@gmail.comx"));
    }

    public static String genarateToken() {
        String jws = Jwts.builder()
                .setSubject("Bank").signWith(key)
                .setIssuer("itphutran.com@gmail.com")
                .setExpiration(new Date(new Date().getTime() + 10000))
                .setNotBefore(new Date())
                .setIssuedAt(new Date())
                .compact();
        return jws;
    }

    public static boolean verify(String token, String username) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getIssuer().equals(username);
    }
}
