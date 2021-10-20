package de.neuefische.devquiz.security.controller;

import de.neuefische.devquiz.security.model.AppUser;
import de.neuefische.devquiz.security.service.JWTUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("auth/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtilsService jwtUtilsService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JWTUtilsService jwtUtilsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtilsService = jwtUtilsService;
    }

    @PostMapping
    public String login(@RequestBody AppUser appUser){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));

        return jwtUtilsService.createToken(new HashMap<>(), appUser.getUsername());
    }

}
