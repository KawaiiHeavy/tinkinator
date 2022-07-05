package com.example.app.services;

import com.example.app.dto.AnswerDTO;
import com.example.app.models.Answer;

import java.util.List;
import java.util.UUID;

public interface AnswerService {

    Answer addAnswer(Answer answer);
    List<Answer> findAllAnswers();
    Answer updateAnswer(Answer answer);
    Answer findAnswerById(UUID id);
    void deleteAnswer(UUID id);
    AnswerDTO findAnswerByQuestionId(UUID questionId);
}
