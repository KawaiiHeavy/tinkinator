package com.example.app.services.impl;

import com.example.app.dto.QuestionDTO;
import com.example.app.exceptions.QuestionNotFoundException;
import com.example.app.models.Answer;
import com.example.app.models.Question;
import com.example.app.models.Solution;
import com.example.app.repositories.AnswerRepository;
import com.example.app.repositories.QuestionRepository;
import com.example.app.repositories.SolutionRepository;
import com.example.app.services.QuestionService;
import com.example.app.utils.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Log4j2
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private AnswerRepository answerRepository;
    private SolutionRepository solutionRepository;
    private QuestionRepository questionRepository;
    private Mapper mapper;

    public QuestionDTO addQuestion(QuestionDTO questionDTO) {

        Question question = mapper.mapToQuestion(questionDTO);

        Set<Answer> answers = question.getAnswers();
        if (answers != null){
            answers.forEach(answer -> {
                Solution solutionFromAns = answer.getSolution();

                Optional<Solution> solution = solutionRepository
                        .findSolutionBySolutionText(solutionFromAns.getSolutionText());
                solution.ifPresent(answer::setSolution);
            });
        }
        Question questionFromDB = questionRepository.save(question);
        return mapper.mapToQuestionDTO(questionFromDB);
    }

    public List<QuestionDTO> findAllQuestions() {
        List<QuestionDTO> questions = mapper.mapToQuestionsDTO(questionRepository.findAll());
        return questions;
    }

    public QuestionDTO updateQuestion(QuestionDTO question) {
        return mapper.mapToQuestionDTO(questionRepository.save(mapper.mapToQuestion(question)));
    }

    public QuestionDTO findQuestionById(UUID id) {
        return mapper.mapToQuestionDTO(questionRepository.findQuestionById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question by id " + id + " was not found")));
    }

    @Transactional
    public void deleteQuestion(UUID id) {
        List<Answer> answers = answerRepository.findAnswersByQuestionId(id);
        for (Answer answer : answers){
            answer.setQuestion(null);
        }
        questionRepository.deleteQuestionById(id);
    }
}
