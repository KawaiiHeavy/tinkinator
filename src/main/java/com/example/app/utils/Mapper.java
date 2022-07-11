package com.example.app.utils;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.models.Answer;
import com.example.app.models.Question;
import com.example.app.models.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Mapper {

    public AnswerDTO mapToAnswerDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setAnswerText(answer.getAnswerText());
        answerDTO.setSolution(answer.getSolution());

        return answerDTO;
    }

    public QuestionDTO mapToQuestionDTO(Question question){

        Streams<Answer> streams = new Streams<>();

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setRoot(question.isRoot());
        Set<AnswerDTO> answers = question.getAnswers().stream()
                .collect(HashSet::new,
                        (set, ans) -> set.add(mapToAnswerDTO(ans)),
                        HashSet::addAll);
        questionDTO.setAnswers(answers);

        return questionDTO;
    }

    public List<QuestionDTO> mapToQuestionsDTO(List<Question> questions){

        Streams<Question> streams = new Streams<>();

        return questions.stream()
                .collect(ArrayList::new,
                        (list, quest) -> list.add(mapToQuestionDTO(quest)),
                        ArrayList::addAll);
    }

}
