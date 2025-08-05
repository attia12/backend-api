package com.example.spring_security_asymetric_encrytion.auth;


import com.example.spring_security_asymetric_encrytion.auth.request.AuthenticationRequest;
import com.example.spring_security_asymetric_encrytion.auth.request.RefreshRequest;
import com.example.spring_security_asymetric_encrytion.auth.request.RegistrationRequest;
import com.example.spring_security_asymetric_encrytion.auth.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request);

    void register(RegistrationRequest request);

    AuthenticationResponse refreshToken(RefreshRequest req);
}
