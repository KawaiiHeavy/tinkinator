package com.example.app.services;

import com.example.app.exceptions.QuestionNotFoundException;
import com.example.app.models.Question;
import com.example.app.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question addQuestion(Question question) {
        question.setId(UUID.randomUUID());
        return questionRepository.save(question);
    }

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findQuestionById(UUID id) {
        return questionRepository.findQuestionById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question by id " + id + " was not found"));
    }

    public void deleteQuestion(UUID id) {
        questionRepository.deleteQuestionById(id);
    }
}
