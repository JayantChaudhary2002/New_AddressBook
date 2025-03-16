package com.example.newAddressBook.controller;

import com.example.newAddressBook.dto.AuthUserDTO;
import com.example.newAddressBook.dto.LoginDTO;
import com.example.newAddressBook.model.AuthUser;
import com.example.newAddressBook.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Authentication", description = "APIs for User Registration and Login")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Register a new user", description = "Creates a new user in the system",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request data")
            })
    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@RequestBody AuthUserDTO authUserDTO) {
        AuthUser registeredUser = authenticationService.register(authUserDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @Operation(summary = "User login", description = "Logs in a user and returns a JWT token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User logged in successfully"),
                    @ApiResponse(responseCode = "401", description = "Invalid credentials")
            })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Optional<String> token = authenticationService.login(loginDTO);
        return token.map(t -> ResponseEntity.ok("Bearer " + t))
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }
}
