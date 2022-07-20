package com.example.app.services;

import com.example.app.dto.QuestionDTO;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    QuestionDTO addQuestion(QuestionDTO question);
    List<QuestionDTO> findAllQuestions();
    QuestionDTO updateQuestion(QuestionDTO question);
    QuestionDTO findQuestionById(UUID id);
    void deleteQuestion(UUID id);

}
