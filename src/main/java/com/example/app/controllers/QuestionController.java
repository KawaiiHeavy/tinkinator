package com.example.app.controllers;

import com.example.app.dto.QuestionDTO;
import com.example.app.models.Question;
import com.example.app.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        List<QuestionDTO> questions = questionService.findAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") UUID id) {
        Question question = questionService.findQuestionById(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<QuestionDTO> addQuestion(@RequestBody Question question) {
        QuestionDTO newQuestion = questionService.addQuestion(question);
        return new ResponseEntity<>(newQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        Question updateQuestion = questionService.updateQuestion(question);
        return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") UUID id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
