package com.imashevdt11.store.controllers;

import com.imashevdt11.store.commons.EndpointConstants;
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
@RequestMapping(EndpointConstants.AUTH_ENDPOINT)
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/register/user")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(service.registerAdmin(request));
    }
}