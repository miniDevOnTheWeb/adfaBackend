package com.adfa.adfa.web.controller;

import com.adfa.adfa.model.dto.LoginRequest;
import com.adfa.adfa.model.entity.UserEntity;
import com.adfa.adfa.service.JwtService;
import com.adfa.adfa.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String token = jwtService.generateToken(request.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Login exitoso",
                "token", token
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<?> refreshUserData (Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        System.out.println(username);
        UserEntity user = userService.findByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
