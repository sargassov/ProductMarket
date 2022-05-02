package com.geekbrains.spring.web.auth.controllers;

import com.geekbrains.spring.web.api.exceptions.AppError;
import com.geekbrains.spring.web.auth.dto.JwtRequest;
import com.geekbrains.spring.web.auth.dto.JwtResponse;
import com.geekbrains.spring.web.auth.dto.RegRequest;
import com.geekbrains.spring.web.auth.exceptions.ValidationException;
import com.geekbrains.spring.web.auth.services.UserService;
import com.geekbrains.spring.web.auth.utils.JwtTokenUtil;
import com.geekbrains.spring.web.auth.validators.AuthValidator;
import com.geekbrains.spring.web.auth.validators.RegValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final AuthValidator authValidator;
    private final RegValidator regValidator;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        authValidator.validate(authRequest);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError("AUTH_SERVICE_INCORRECT_USERNAME_OR_PASSWORD", "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/reg")
    public ResponseEntity<?> createNewUser(@RequestBody RegRequest regRequest) {
        try {
            regValidator.validate(regRequest);
        } catch (ValidationException e) {
            return new ResponseEntity<>(new AppError("REG_SERVICE_INCORRECT_USERNAME_OR_PASSWORD", e.getErrorFieldsMessages().toString()), HttpStatus.UNAUTHORIZED);
        }
        userService.addNewUserInDatabase(regRequest.getLogin(), regRequest.getPassword(), regRequest.getEmail());
        return ResponseEntity.ok("New user " + regRequest.getLogin() + " already in database!");
    }
}
