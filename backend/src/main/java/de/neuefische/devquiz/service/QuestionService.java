package de.neuefische.devquiz.service;

import de.neuefische.devquiz.model.Answer;
import de.neuefische.devquiz.model.Question;
import de.neuefische.devquiz.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepo questionRepo;

    @Autowired
    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public Question addQuestion(Question newQuestion){
        return questionRepo.save(newQuestion);
    }


    public Question get(String id) {
        Optional<Question> optionalQuestion = questionRepo.findById(id);

        if (optionalQuestion.isEmpty()) {
            throw new NoSuchElementException("Question with id:" + id + " not found!");
        }

        return optionalQuestion.get();
    }

    public String validateAnswer(String id) {
        Optional<Question> answeredQuestionOptional = questionRepo.findById(id);
        String correctAnswer = "";
        if (answeredQuestionOptional.isPresent()) {
            Question answeredQuestion = answeredQuestionOptional.get();
            for (Answer answer : answeredQuestion.getAnswers()) {
                if (answer.getIsCorrect()) {
                    correctAnswer = answer.getAnswerText();
                    return correctAnswer;
                } else {
                    throw new NoSuchElementException("no correct answer found I");
                }
            }
        } else {
            throw new NoSuchElementException("no correct answer found");
        }
        return correctAnswer;
    }



}
