package de.neuefische.devquiz.controller;

import de.neuefische.devquiz.model.Answer;
import de.neuefische.devquiz.model.Question;
import de.neuefische.devquiz.repo.QuestionRepo;
import de.neuefische.devquiz.security.model.AppUser;
import de.neuefische.devquiz.security.repo.AppUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private QuestionRepo questionRepo;

    @BeforeEach
    public void clearDb() {
        questionRepo.deleteAll();
    }


//    private HttpHeaders headers = getHttpHeadersWithJWT();

    @Test
    @DisplayName("Should return a list with all questions from db")
    void testListQuestion() {
        // GIVEN
        HttpHeaders headers = getHttpHeadersWithJWT();

        questionRepo.save(new Question("1", "Question with ID '1'", List.of()));
        questionRepo.save(new Question("2", "Question with ID '2'", List.of()));
        questionRepo.save(new Question("3", "Question with ID '3'", List.of()));
        // WHEN
        ResponseEntity<Question[]> responseEntity = testRestTemplate.exchange("/api/question", HttpMethod.GET, new HttpEntity<>(headers), Question[].class);
        // THEN
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), arrayContainingInAnyOrder(
                new Question("1", "Question with ID '1'", List.of()),
                new Question("2", "Question with ID '2'", List.of()),
                new Question("3", "Question with ID '3'", List.of())
        ));

    }

    @Test
    @DisplayName("Should return a question object with the given id")
    void testValidate() {
        // GIVEN
        HttpHeaders headers = getHttpHeadersWithJWT();

        Question question = new Question("302", "Question with ID '302'", List.of(new Answer("1", "correct", true)));

        questionRepo.save(question);
        // WHEN
        ResponseEntity<String> responseEntity = testRestTemplate.exchange("/api/question/validate/" + question.getId(), HttpMethod.GET, new HttpEntity<>(headers), String.class);
        // THEN
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), is("correct"));

    }

    @Test
    @DisplayName("Should add a new question item to the db")
    void testAddQuestion() {
        // GIVEN
        HttpHeaders headers = getHttpHeadersWithJWT();

        Question questionToAdd = new Question("33", "This is a question", List.of());


        // WHEN
        ResponseEntity<Question> postResponseEntity = testRestTemplate.exchange("/api/question/", HttpMethod.POST, new HttpEntity<>(questionToAdd, headers), Question.class);
        Question actual = postResponseEntity.getBody();

        // THEN
        assertThat(postResponseEntity.getStatusCode(), is(HttpStatus.OK));
        assertNotNull(actual);
        assertThat(actual, is(new Question("33", "This is a question", List.of())));
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