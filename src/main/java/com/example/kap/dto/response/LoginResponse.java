package com.example.kap.dto.response;

public class LoginResponse {
    private String token;



    public LoginResponse(String token){
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
