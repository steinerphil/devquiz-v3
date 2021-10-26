package de.neuefische.devquiz.service;

import de.neuefische.devquiz.model.CodeToAccessToken;
import de.neuefische.devquiz.model.githubApi.GithubApiResponse;
import de.neuefische.devquiz.service.api.GithubApiService;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    private final GithubApiService githubApiService;

    public OAuthService(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    public String getGithubAccessToken(CodeToAccessToken codeToAccessToken) {
        GithubApiResponse githubApiResponse = githubApiService.getGithubAccessToken(codeToAccessToken);
        return githubApiResponse.toString();
    }
}