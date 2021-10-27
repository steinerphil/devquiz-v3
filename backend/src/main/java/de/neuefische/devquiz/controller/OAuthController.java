package de.neuefische.devquiz.controller;

import de.neuefische.devquiz.model.CodeToAccessToken;
import de.neuefische.devquiz.model.GithubConfig;
import de.neuefische.devquiz.security.service.JWTUtilsService;
import de.neuefische.devquiz.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("auth")
public class OAuthController {

    private final OAuthService oauthService;
    private final JWTUtilsService jwtUtilsService;
    private final GithubConfig githubConfig;

    @Value("${neuefische.devquiz.github.secret}")
    private String githubSecret;


    @Autowired
    public OAuthController(OAuthService oauthService, JWTUtilsService jwtUtilsService, GithubConfig githubConfig) {
        this.oauthService = oauthService;
        this.jwtUtilsService = jwtUtilsService;
        this.githubConfig = githubConfig;
    }

    @PostMapping("github")
    public String loginViaGithub(@RequestBody CodeToAccessToken input) {
        CodeToAccessToken codeToAccessToken = new CodeToAccessToken(githubConfig.getClientId(), githubSecret , input.getCode());
        String githubUsername = oauthService.getUserNameWithAccessToken(codeToAccessToken);
        return jwtUtilsService.createToken(new HashMap<>(), githubUsername);
    }

    @GetMapping("config")
    private GithubConfig getConfig(){
        return githubConfig;
    }

}