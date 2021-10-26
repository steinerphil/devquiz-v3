package de.neuefische.devquiz.service;

import de.neuefische.devquiz.model.CodeToAccessToken;
import de.neuefische.devquiz.model.UserResponseDto;
import de.neuefische.devquiz.model.githubApi.GithubApiResponse;
import de.neuefische.devquiz.service.api.GithubApiService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    private final GithubApiService githubApiService;


    public OAuthService(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    private String getGithubAccessToken(CodeToAccessToken codeToAccessToken) {
        GithubApiResponse githubApiResponse = githubApiService.getGithubAccessToken(codeToAccessToken);
       return githubApiResponse.getAccessToken();
    }

    public String getUserNameWithAccessToken(CodeToAccessToken codeToAccessToken){
        String accessToken = getGithubAccessToken(codeToAccessToken);
      return githubApiService.getUserNameWithAccessToken(accessToken);
    }
}