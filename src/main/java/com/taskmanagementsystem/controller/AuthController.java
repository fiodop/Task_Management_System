package com.taskmanagementsystem.controller;

import com.taskmanagementsystem.model.dto.AuthenticationRequest;
import com.taskmanagementsystem.model.dto.AuthenticationResponse;
import com.taskmanagementsystem.model.dto.RegisterRequest;
import com.taskmanagementsystem.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Authorization controller")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "register user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully registered")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request

    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(
            summary = "authentication user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully authenticated"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request

    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
