package com.example.kap.service;

public interface JwtService {

    String generateToken(
            String username,
            String role
    );

    String extractUsername(String token);

    String extractRole(String token);

    boolean isTokenValid(
            String token,
            String username
    );
}