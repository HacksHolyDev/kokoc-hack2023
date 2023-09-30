package com.holydev.sportcharity.controllers;


import com.holydev.sportcharity.DTO.Auth.AuthMailRequest;
import com.holydev.sportcharity.DTO.Auth.AuthResponse;
import com.holydev.sportcharity.DTO.Auth.AuthUNameRequest;
import com.holydev.sportcharity.DTO.Auth.Register.RegistrationRequest;
import com.holydev.sportcharity.global_utilities.AuthorityAnnotations.AdminAuth;
import com.holydev.sportcharity.global_utilities.AuthorityAnnotations.UserAuth;
import com.holydev.sportcharity.services.Security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegistrationRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateEmail(
            @RequestBody AuthMailRequest request
    ) {
        return ResponseEntity.ok(service.authenticateEmail(request));
    }


    @PostMapping("/login/uname")
    public ResponseEntity<AuthResponse> authenticateUName(
            @RequestBody AuthUNameRequest request
    ) {
        return ResponseEntity.ok(service.authenticateUName(request));
    }

    @UserAuth
    @GetMapping("/test")
    public void test() {
        System.out.println("Success!");
    }

    @AdminAuth
    @GetMapping("/test2")
    public void test2() {
        System.out.println("Success!");
    }

}
