package com.megalabsapi.model.service.impl;

import com.megalabsapi.model.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    // Generar el token JWT
    // Usa una clave segura de al menos 256 bits
    private Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)  // El subject es el nombre de usuario (email, por ejemplo)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 horas de expiración
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Obtener el nombre de usuario (email) del token
    @Override
    public String getUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Validar el token
    @Override
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);  // Valida si el token no ha expirado
    }

    // Comprobar si el token ha expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // Extraer todos los claims del token JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}

