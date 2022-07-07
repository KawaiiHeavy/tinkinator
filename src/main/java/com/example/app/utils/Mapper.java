package com.example.app.utils;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.models.Answer;
import com.example.app.models.Question;
import com.example.app.models.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    @Autowired
    private Streams streams;

    public AnswerDTO mapToAnswerDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setAnswerText(answer.getAnswerText());
        return answerDTO;
    }

    public QuestionDTO mapToQuestionDTO(Question question){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setRoot(question.isRoot());
        questionDTO.setAnswers(Streams.of(question.getAnswers()).transform(answer -> mapToAnswerDTO((Answer) answer)).toSet());

        return questionDTO;
    }

}
