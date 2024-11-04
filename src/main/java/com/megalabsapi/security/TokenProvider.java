package com.megalabsapi.security;

import com.megalabsapi.exception.RoleNotFoundException;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.repository.RepresentanteRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TokenProvider {
    private final RepresentanteRepository representanteRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.validity-in-seconds}")
    private long jwtValidityInSeconds;

    private Key key;
    private JwtParser jwtParser;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build();
    }

    // Generar tokens JWT
    public String createAccessToken(Authentication authentication) {
        // Obtener el DNI del representante autenticado desde el principal
        String dni = authentication.getName();

        // Buscar el representante en la base de datos usando el DNI
        Representante representante = representanteRepository
                .findByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el DNI: " + dni));

        // Obtener el rol del usuario
        String role = authentication
                .getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(RoleNotFoundException::new)
                .getAuthority();

        // Crear el token JWT con solo el rol y el sujeto
        return Jwts
                .builder()
                .setSubject(dni)  // El sujeto ahora es el DNI del representante
                .claim("role", role)  // Solo se incluye el rol como claim
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis() + jwtValidityInSeconds * 1000))
                .compact();
    }

    // Obtener autenticación
    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        String role = claims.get("role").toString();

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        // El principal ahora es el DNI del representante, extraído del sujeto del JWT
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    // Validar tokens JWT
    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
