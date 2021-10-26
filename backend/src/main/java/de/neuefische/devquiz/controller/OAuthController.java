package de.neuefische.devquiz.controller;

import de.neuefische.devquiz.model.CodeToAccessToken;
import de.neuefische.devquiz.security.service.JWTUtilsService;
import de.neuefische.devquiz.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${neuefische.devquiz.github.secret}")
    private String githubSecret;

    @Autowired
    public OAuthController(OAuthService oauthService, JWTUtilsService jwtUtilsService) {
        this.oauthService = oauthService;
        this.jwtUtilsService = jwtUtilsService;
    }

    @PostMapping("github")
    public String loginViaGithub(@RequestBody CodeToAccessToken code) {
        CodeToAccessToken codeToAccessToken = new CodeToAccessToken("be41cda045d6d9ff7970", githubSecret , code.getCode());
        String githubUsername = oauthService.getUserNameWithAccessToken(codeToAccessToken);
        return jwtUtilsService.createToken(new HashMap<>(), githubUsername);
    }


}