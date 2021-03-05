package de.stuckenbrockhm.demo.monitoring.auth.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.stuckenbrockhm.demo.monitoring.api.AuthBaseApi;
import de.stuckenbrockhm.demo.monitoring.auth.services.AuthService;

import lombok.extern.java.Log;

@Log
@RestController
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/auth") //, consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthBaseApi> save(@RequestParam(name = "username") String username,
                     @RequestParam(name = "passwordHash") String passwordHash) throws NoSuchAlgorithmException {

        AuthBaseApi auth = authService.authenticateUser(username, passwordHash);
        return new ResponseEntity<>(auth, HttpStatus.OK);
    }

}