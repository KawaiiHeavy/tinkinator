package com.example.app.services;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.dto.SolutionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    void attachQuestion(UUID questionId, UUID answerId);
    Integer countAllAnswers();
    Page<AnswerDTO> getAllAnswersPaging(Pageable paging);
}
