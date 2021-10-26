package de.neuefische.devquiz.controller;

import de.neuefische.devquiz.model.CodeToAccessToken;
import de.neuefische.devquiz.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class OAuthController {

    private final OAuthService oauthService;

    @Autowired
    public OAuthController(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @PostMapping("github")
    public String loginViaGithub(@RequestBody CodeToAccessToken codeToAccessToken) {
        System.out.println(codeToAccessToken);
        return oauthService.getGithubAccessToken(codeToAccessToken);
    }


}