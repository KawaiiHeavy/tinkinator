package com.example.app.services;

import com.example.app.dto.AnswerDTO;
import com.example.app.exceptions.AnswerNotFoundException;
import com.example.app.models.Answer;
import com.example.app.repositories.AnswerRepository;
import com.example.app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private Mapper mapper;

    public Answer addAnswer(Answer answer) {
        answer.setId(UUID.randomUUID());
        return answerRepository.save(answer);
    }

    public List<Answer> findAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer updateAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer findAnswerById(UUID id) {
        return answerRepository.findAnswerById(id)
                .orElseThrow(() -> new AnswerNotFoundException("Answer by id " + id + " was not found"));
    }

    public AnswerDTO findAnswerByQuestionId(UUID questionId){
        return mapper.mapToAnswerDTO(answerRepository.findAnswerByQuestionId(questionId)
                .orElseThrow(() -> new AnswerNotFoundException("Answer with questionId " + questionId + " was not found")));
    }

    @Transactional
    public void deleteAnswer(UUID id) {
        answerRepository.deleteAnswerById(id);
    }
}
