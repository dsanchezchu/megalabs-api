package com.megalabsapi.security;

import com.megalabsapi.entity.Representante;
import com.megalabsapi.repository.RepresentanteRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;


public class TokenProvider {
    public String createAccessToken(Authentication authentication) {
        String email = authentication.getName();

        // Cambia esto si tu método para encontrar el usuario es diferente
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        String role = authentication.getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"))
                .getAuthority();

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("role", role)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis() + jwtValidityInSeconds * 1000))
                .compact();
    }
}
