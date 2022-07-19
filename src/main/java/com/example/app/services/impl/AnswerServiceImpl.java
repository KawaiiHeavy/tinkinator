package com.example.app.services.impl;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.dto.SolutionDTO;
import com.example.app.exceptions.AnswerNotFoundException;
import com.example.app.exceptions.QuestionNotFoundException;
import com.example.app.exceptions.SolutionNotFoundException;
import com.example.app.models.Answer;
import com.example.app.models.Question;
import com.example.app.models.Solution;
import com.example.app.repositories.AnswerRepository;
import com.example.app.repositories.QuestionRepository;
import com.example.app.services.AnswerService;
import com.example.app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private Mapper mapper;

    public AnswerDTO addAnswer(AnswerDTO answer) {
        return mapper.mapToAnswerDTO(answerRepository.save(mapper.mapToAnswer(answer)));
    }

    public List<AnswerDTO> findAllAnswers() {
        List<AnswerDTO> answers = new LinkedList<>();
        answerRepository.findAll().forEach(answer ->
                answers.add(mapper.mapToAnswerDTO(answer)));
        return answers;
    }

    public AnswerDTO updateAnswer(AnswerDTO answer) {
        return mapper.mapToAnswerDTO(answerRepository.save(mapper.mapToAnswer(answer)));
    }

    public AnswerDTO findAnswerById(UUID id) {
        return mapper.mapToAnswerDTO(answerRepository.findAnswerById(id)
                .orElseThrow(() -> new AnswerNotFoundException("Answer by id " + id + " was not found")));
    }

    public QuestionDTO findQuestionByAnswerId(UUID id) {
        Optional<Question> question = answerRepository.findQuestionByAnswerId(id);
        return mapper.mapToQuestionDTO(question.orElseThrow(()
                -> new QuestionNotFoundException("Question by answerId " + id + " was not found")));
    }

    public SolutionDTO findSolutionByAnswerId(UUID id) {
        Optional<Solution> solution = answerRepository.findSolutionByAnswerId(id);
        return mapper.mapToSolutionDTO(solution.orElseThrow(()
                -> new SolutionNotFoundException("Solution by answerId " + id + " was not found")));
    }

    public void attachQuestion(UUID questionId, UUID answerId) {
        Optional<Question> question = questionRepository.findById(questionId);
        answerRepository.addQuestionToAnswer(question.get(), answerId);
    }

    @Transactional
    public void deleteAnswer(UUID id) {
        Optional<Answer> optionalAnswer = answerRepository.findAnswerById(id);
        optionalAnswer.ifPresent(answer -> {
            System.out.println(answer);

            for (Question question : questionRepository.findAll()){
                Set<Answer> answers = question.getAnswers();
                if (answers.contains(answer)){
                    answers.remove(answer);
                }
            }

            if (answer.getQuestion() != null) {
                answer.setQuestion(null);
            }
            if (answer.getSolution() != null) {
                answer.setSolution(null);
            }

            answerRepository.delete(answer);
        });
    }
}