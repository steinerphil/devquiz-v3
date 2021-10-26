package de.neuefische.devquiz.service.api;

import de.neuefische.devquiz.model.CodeToAccessToken;
import de.neuefische.devquiz.model.githubApi.GithubApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubApiService {

    static final String API_URL = "https://github.com/login/oauth/access_token";

    private final RestTemplate restTemplate = new RestTemplate();

    public GithubApiResponse getGithubAccessToken(CodeToAccessToken codeToAccessToken) {

        ResponseEntity<GithubApiResponse> response = restTemplate.exchange("https://github.com/login/oauth/access_token", HttpMethod.POST, new HttpEntity<>(codeToAccessToken), GithubApiResponse.class);
        return response.getBody();

    }
}
