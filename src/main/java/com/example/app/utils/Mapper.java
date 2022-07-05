package com.example.app.utils;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.SolutionDTO;
import com.example.app.models.Answer;
import com.example.app.models.Solution;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public AnswerDTO mapToAnswerDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setAnswerText(answer.getAnswerText());
        return answerDTO;
    }

    public SolutionDTO mapToSolutionDTO(Solution solution) {
        SolutionDTO solutionDTO = new SolutionDTO();
        solutionDTO.setSolutionText(solution.getSolutionText());
        return solutionDTO;
    }

}
