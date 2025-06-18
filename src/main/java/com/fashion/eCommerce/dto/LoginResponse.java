package com.fashion.eCommerce.dto;

// LoginResponse.java
public class LoginResponse {
    private String token;

    public String getEmail() {
        return email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String email;
    // constructor, getters

    public LoginResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }
}

