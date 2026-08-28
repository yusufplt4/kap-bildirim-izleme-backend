package com.example.kap.controller;

import com.example.kap.constant.PathConstant;
import com.example.kap.dto.request.LoginRequest;
import com.example.kap.dto.request.RegisterRequest;
import com.example.kap.dto.response.LoginResponse;
import com.example.kap.service.AuthenticationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstant.AUTH_BASE_PATH)
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(
            AuthenticationService authenticationService
    ) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(PathConstant.AUTH_REGISTER)
    public String register(
            @RequestBody RegisterRequest request
    ) {
        return authenticationService.register(request);
    }

    @PostMapping(PathConstant.AUTH_LOGIN)
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {
        return authenticationService.login(request);
    }
}