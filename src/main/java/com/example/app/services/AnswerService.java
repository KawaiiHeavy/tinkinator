package com.example.app.services;

import com.example.app.models.Answer;
import com.example.app.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Optional<Answer> findById(UUID id){
        return answerRepository.findById(id);
    }

}
