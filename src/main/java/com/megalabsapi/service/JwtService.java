package com.megalabsapi.service;

public interface JwtService {
    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean isTokenValid(String token);
}
