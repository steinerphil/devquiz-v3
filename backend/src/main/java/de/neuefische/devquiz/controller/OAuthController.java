package de.neuefische.devquiz.controller;

import de.neuefische.devquiz.model.CodeToAccessToken;
import de.neuefische.devquiz.security.model.AppUser;
import de.neuefische.devquiz.security.service.JWTUtilsService;
import de.neuefische.devquiz.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("auth")
public class OAuthController {

    private final OAuthService oauthService;
    private final JWTUtilsService jwtUtilsService;

    @Autowired
    public OAuthController(OAuthService oauthService, JWTUtilsService jwtUtilsService) {
        this.oauthService = oauthService;
        this.jwtUtilsService = jwtUtilsService;
    }

    @PostMapping("github")
    public String loginViaGithub(@RequestBody CodeToAccessToken code) {
        CodeToAccessToken codeToAccessToken = new CodeToAccessToken("be41cda045d6d9ff7970", "30f646dbf093fe95ca901dae4e0fea444826319e", code.getCode());
       String githubUsername = oauthService.getUserNameWithAccessToken(codeToAccessToken);
       return jwtUtilsService.createToken(new HashMap<>(), githubUsername);
    }


}