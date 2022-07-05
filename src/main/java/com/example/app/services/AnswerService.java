package com.example.app.services;

import com.example.app.exceptions.AnswerNotFoundException;
import com.example.app.models.Answer;
import com.example.app.models.Answer;
import com.example.app.repositories.AnswerRepository;
import com.example.app.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer addAnswer(Answer answer) {
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

    @Transactional
    public void deleteAnswer(UUID id) {
        answerRepository.deleteAnswerById(id);
    }
}
