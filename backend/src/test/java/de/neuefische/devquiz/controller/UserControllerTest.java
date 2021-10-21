package de.neuefische.devquiz.controller;

import de.neuefische.devquiz.model.UserResponseDto;
import de.neuefische.devquiz.security.model.AppUser;
import de.neuefische.devquiz.security.repo.AppUserRepo;
import de.neuefische.devquiz.security.service.JWTUtilsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {


    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @SpyBean
    private JWTUtilsService jwtUtilsServiceMock;


    @Test
    void getLoggedin_UserWithValidToken(){
        //G
        HttpHeaders headers = getHttpHeadersWithJWT();

        //W
        ResponseEntity<UserResponseDto> response = testRestTemplate.exchange("/api/user/me", HttpMethod.GET, new HttpEntity<>(headers), UserResponseDto.class);

        //T
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getUsername(), is("testuser"));
    }

    @Test
    void getLoggedin_UserWithInvalidToken(){
        //G

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("dies.istkein.token");
        //W
        ResponseEntity<UserResponseDto> response = testRestTemplate.exchange("/api/user/me", HttpMethod.GET, new HttpEntity<>(headers), UserResponseDto.class);

        //T
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    void getLoggedin_UserWithExpiredToken(){
        //Given
        when(jwtUtilsServiceMock.createDuration()).thenReturn(1L);
        HttpHeaders headers = getHttpHeadersWithJWT();

        //When
        ResponseEntity<UserResponseDto> response = testRestTemplate.exchange("/api/user/me", HttpMethod.GET, new HttpEntity<>(headers), UserResponseDto.class);

        //Then
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    private HttpHeaders getHttpHeadersWithJWT() {
        appUserRepo.save(AppUser.builder().username("testuser").password(passwordEncoder.encode("9999")).build());
        AppUser loginData = new AppUser("testuser", "9999");
        ResponseEntity<String> response = testRestTemplate.postForEntity("/auth/login", loginData, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(response.getBody());
        return headers;
    }

}