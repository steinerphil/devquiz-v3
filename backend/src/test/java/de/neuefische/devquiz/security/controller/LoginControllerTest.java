package de.neuefische.devquiz.security.controller;

import de.neuefische.devquiz.security.model.AppUser;
import de.neuefische.devquiz.security.repo.AppUserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${neuefische.devquiz.jwt.secret}")
    private String JWT_SECRET;

    @Test
    void loginWithValidCredentials_ReturnValidJWT() {

        //Given
        appUserRepo.save(AppUser.builder()
                .username("test-user")
                .password(passwordEncoder.encode("9999"))
                .build());

        //When
        AppUser appUser = new AppUser("user", "9999");
        ResponseEntity<String> response = testRestTemplate.postForEntity("/auth/login", appUser, String.class);

        //Then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        //extract claims from token
        Claims body = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(response.getBody())
                .getBody();
        assertThat(body.getSubject(), is("user"));

    }

    @Test
    void loginWithInvalidCredentials_ReturnError(){

        //Given
        appUserRepo.save(AppUser.builder()
                .username("test-user")
                .password(passwordEncoder.encode("9999"))
                .build());

        //When
        AppUser appUser = new AppUser("user", "WRONG_TESTS1");
        ResponseEntity<String> response = testRestTemplate.postForEntity("/auth/login", appUser, String.class);

        //Then
        assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));

    }
}