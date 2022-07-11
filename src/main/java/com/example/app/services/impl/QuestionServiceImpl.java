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
import com.example.app.utils.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private Mapper mapper;

    public QuestionDTO addQuestion(Question question) {
        System.out.println(question);
        question.getAnswers().forEach(answer -> {
            Solution solutionFromAns = answer.getSolution();
            Optional<Solution> solution = solutionRepository
                    .findSolutionBySolutionText(solutionFromAns.getSolutionText());
            solution.ifPresent(answer::setSolution);
        });
        Question questionFromDB = questionRepository.save(question);
        return mapper.mapToQuestionDTO(questionFromDB);
    }

    public List<QuestionDTO> findAllQuestions() {
        List<QuestionDTO> questions = mapper.mapToQuestionsDTO(questionRepository.findAll());
        System.out.println(questions);
        return questions;

    }

    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findQuestionById(UUID id) {
        return questionRepository.findQuestionById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question by id " + id + " was not found"));
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
