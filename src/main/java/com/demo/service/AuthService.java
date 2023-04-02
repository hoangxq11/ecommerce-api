package com.demo.service;

import com.demo.web.dto.request.LoginRequest;
import com.demo.web.dto.request.SignupRequest;
import com.demo.web.dto.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateAccount(LoginRequest loginRequest);
    void registerAccount(SignupRequest signupRequest);

    void removeAccount(Integer accountId);
}
