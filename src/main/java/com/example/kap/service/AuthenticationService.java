package com.example.kap.service;

import com.example.kap.dto.request.LoginRequest;
import com.example.kap.dto.request.RegisterRequest;
import com.example.kap.dto.response.LoginResponse;

public interface AuthenticationService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}