package com.example.newAddressBook.controller;

import com.example.newAddressBook.dto.AuthUserDTO;
import com.example.newAddressBook.dto.LoginDTO;
import com.example.newAddressBook.model.AuthUser;
import com.example.newAddressBook.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@RequestBody AuthUserDTO authUserDTO) {
        AuthUser registeredUser = authenticationService.register(authUserDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Optional<String> token = authenticationService.login(loginDTO);
        return token.map(t -> ResponseEntity.ok("Bearer " + t))
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }
}
