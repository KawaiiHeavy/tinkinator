package com.example.app.services;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.dto.SolutionDTO;
import com.example.app.models.Answer;
import com.example.app.models.Solution;

import java.util.List;
import java.util.UUID;

public interface AnswerService {

    AnswerDTO addAnswer(AnswerDTO answer);
    List<AnswerDTO> findAllAnswers();
    AnswerDTO updateAnswer(AnswerDTO answer);
    AnswerDTO findAnswerById(UUID id);
    void deleteAnswer(UUID id);
    QuestionDTO findQuestionByAnswerId(UUID id);
    SolutionDTO findSolutionByAnswerId(UUID id);
}
