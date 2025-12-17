package com.example.JiraIntTest.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JiraIntTest.dto.LoginRequest;
import com.example.JiraIntTest.entity.User;
import com.example.JiraIntTest.service.AuthService;
import com.example.JiraIntTest.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(Map.of(
                "message", "User registered",
                "username", savedUser.getUsername()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        String jwtToken = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "token", jwtToken
        ));
    }
}


