package com.example.app.services;

import com.example.app.models.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    Question addQuestion(Question question);
    List<Question> findAllQuestions();
    Question updateQuestion(Question question);
    Question findQuestionById(UUID id);
    void deleteQuestion(UUID id);
    Question findRandomQuestion();

}
