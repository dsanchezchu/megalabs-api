package com.megalabsapi.model.service;

public interface JwtService {
    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean isTokenValid(String token);
}
