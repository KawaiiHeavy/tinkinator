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
import com.example.app.services.AnswerService;
import com.example.app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerServiceImpl implements AnswerService {

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

    public QuestionDTO findQuestionByAnswerId(UUID id){
        Optional<Question> question = answerRepository.findQuestionByAnswerId(id);
        return mapper.mapToQuestionDTO(question.orElseThrow(()
                -> new QuestionNotFoundException("Question by answerId " + id + " was not found")));
    }

    public SolutionDTO findSolutionByAnswerId(UUID id){
        Optional<Solution> solution = answerRepository.findSolutionByAnswerId(id);
        return mapper.mapToSolutionDTO(solution.orElseThrow(()
                -> new SolutionNotFoundException("Solution by answerId " + id + " was not found")));
    }

    @Transactional
    public void deleteAnswer(UUID id) {
        answerRepository.deleteAnswerById(id);
    }
}
