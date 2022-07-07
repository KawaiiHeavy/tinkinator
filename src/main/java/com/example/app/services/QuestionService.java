package com.example.app.services;

import com.example.app.dto.QuestionDTO;
import com.example.app.models.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    QuestionDTO addQuestion(Question question);
    List<QuestionDTO> findAllQuestions();
    Question updateQuestion(Question question);
    Question findQuestionById(UUID id);
    void deleteQuestion(UUID id);

}
