package com.holydev.sportcharity.controllers;


import com.holydev.sportcharity.DTO.Auth.AuthMailRequest;
import com.holydev.sportcharity.DTO.Auth.AuthResponse;
import com.holydev.sportcharity.DTO.Auth.Register.RegistrationRequest;
import com.holydev.sportcharity.entities.users.Role;
import com.holydev.sportcharity.services.Security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @Operation(summary = "register user")
    @PostMapping("/register/user")
    public ResponseEntity<AuthResponse> registerUser(
            @RequestBody RegistrationRequest request
    ) {
        return ResponseEntity.ok(service.registerAccount(request, Role.USER));
    }

    @Operation(summary = "register admin")
    @PostMapping("/register/admin")
    public ResponseEntity<AuthResponse> registerAdmin(
            @RequestBody RegistrationRequest request
    ) {
        return ResponseEntity.ok(service.registerAccount(request, Role.ADMIN));
    }

    @Operation(summary = "register departament head")
    @PostMapping("/register/dephead")
    public ResponseEntity<AuthResponse> registerDepHead(
            @RequestBody RegistrationRequest request
    ) {
        return ResponseEntity.ok(service.registerAccount(request, Role.DEP_HEAD));
    }

    @Operation(summary = "register fund agent")
    @PostMapping("/register/fundagent")
    public ResponseEntity<AuthResponse> registerFundAgent(
            @RequestBody RegistrationRequest request
    ) {
        return ResponseEntity.ok(service.registerAccount(request, Role.FUND_AGENT));
    }

    @Operation(summary = "login for all accounts")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateEmail(
            @RequestBody AuthMailRequest request
    ) {
        return ResponseEntity.ok(service.authenticateEmail(request));
    }
}
