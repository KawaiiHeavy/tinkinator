package com.example.app.services;

import com.example.app.exceptions.QuestionNotFoundException;
import com.example.app.models.Question;
import com.example.app.repositories.QuestionRepository;
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

    public Question addQuestion(Question question) {
        question.setId(UUID.randomUUID());
        return questionRepository.save(question);
    }

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findQuestionById(UUID id) {
        return questionRepository.findQuestionById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question by id " + id + " was not found"));
    }

    public Question findRandomQuestion(){
        List<Question> questions = questionRepository.findAll();
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }

    @Transactional
    public void deleteQuestion(UUID id) {
        questionRepository.deleteQuestionById(id);
    }
}
