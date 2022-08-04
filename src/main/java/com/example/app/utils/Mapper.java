package com.example.app.utils;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.ClientRequestDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.dto.SolutionDTO;
import com.example.app.models.other.Answer;
import com.example.app.models.other.ClientRequest;
import com.example.app.models.question.Question;
import com.example.app.models.other.Solution;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Mapper {

    public AnswerDTO mapToAnswerDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setAnswerText(answer.getAnswerText());
        Solution solution = answer.getSolution();
        if (solution != null) {
            answerDTO.setSolution(mapToSolutionDTO(solution));
        }

        return answerDTO;
    }

    public Answer mapToAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        answer.setId(answerDTO.getId());
        answer.setAnswerText(answerDTO.getAnswerText());
        if (answerDTO.getSolution() != null) {
            answer.setSolution(mapToSolution(answerDTO.getSolution()));
        }

        return answer;
    }

    public Question mapToQuestion(QuestionDTO questionDTO) {

        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setQuestionText(questionDTO.getQuestionText());
        question.setRoot(questionDTO.isRoot());

//        Set<AnswerDTO> answersFromDB = questionDTO.getAnswers();
//        if (answersFromDB != null) {
//            Set<Answer> answers = answersFromDB.stream()
//                    .collect(HashSet::new, (set, ans) -> set.add(mapToAnswer(ans)),
//                            HashSet::addAll);
//
//            question.setAnswers(answers);
//        }
        return question;
    }

    public QuestionDTO mapToQuestionDTO(Question question, Set<Answer> answers) {

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setRoot(question.isRoot());

        if (answers != null) {
            Set<AnswerDTO> answersDTO = answers.stream()
                    .collect(HashSet::new,
                            (set, ans) -> set.add(mapToAnswerDTO(ans)),
                            HashSet::addAll);
            questionDTO.setAnswers(answersDTO);
        }

        return questionDTO;
    }

    public ClientRequestDTO mapToClientRequestDTO(ClientRequest clientRequest) {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO();
        clientRequestDTO.setId(clientRequest.getId());
        clientRequestDTO.setRequestText(clientRequest.getRequestText());
        return clientRequestDTO;
    }

    public ClientRequest mapToClientRequest(ClientRequestDTO clientRequestDTO) {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setId(clientRequestDTO.getId());
        clientRequest.setRequestText(clientRequestDTO.getRequestText());
        return clientRequest;
    }

    public SolutionDTO mapToSolutionDTO(Solution solution) {
        SolutionDTO solutionDTO = new SolutionDTO();
        solutionDTO.setId(solution.getId());
        solutionDTO.setSolutionText(solution.getSolutionText());

        return solutionDTO;
    }

    public Solution mapToSolution(SolutionDTO solutionDTO) {
        Solution solution = new Solution();
        solution.setId(solutionDTO.getId());
        solution.setSolutionText(solutionDTO.getSolutionText());

        return solution;
    }

}
