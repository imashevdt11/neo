package com.imashevdt11.store.controllers;

import com.imashevdt11.store.dtos.auth.AuthenticationRequest;
import com.imashevdt11.store.dtos.auth.AuthenticationResponse;
import com.imashevdt11.store.dtos.auth.RegistrationRequest;
import com.imashevdt11.store.services.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/user_registration")
    public ResponseEntity<AuthenticationResponse> userRegistration(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @PostMapping("/admin_registration")
    public ResponseEntity<AuthenticationResponse> adminRegistration(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(service.registerAdmin(request));
    }
}