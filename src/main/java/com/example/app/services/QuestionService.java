package com.example.app.services;

import com.example.app.models.Question;
import com.example.app.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> findById(UUID id){
        return questionRepository.findById(id);
    }

}
