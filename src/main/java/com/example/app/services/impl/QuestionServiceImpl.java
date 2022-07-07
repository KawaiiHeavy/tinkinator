package com.example.app.services.impl;

import com.example.app.dto.QuestionDTO;
import com.example.app.exceptions.QuestionNotFoundException;
import com.example.app.models.Question;
import com.example.app.repositories.QuestionRepository;
import com.example.app.services.QuestionService;
import com.example.app.utils.Mapper;
import com.example.app.utils.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private Mapper mapper;

    public QuestionDTO addQuestion(Question question) {

        Question questionFromDB = questionRepository.save(question);
        return mapper.mapToQuestionDTO(question);
    }

    public List<QuestionDTO> findAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return Streams.of(questions).transform(question -> mapper.mapToQuestionDTO((Question) question)).toList();

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
        questionRepository.deleteQuestionById(id);
    }
}
