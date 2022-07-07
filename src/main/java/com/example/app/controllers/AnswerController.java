package com.example.app.controllers;

import com.example.app.dto.AnswerDTO;
import com.example.app.models.Answer;
import com.example.app.services.AnswerService;
import com.example.app.services.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/all")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers = answerService.findAllAnswers();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable("id") UUID id) {
        Answer answer = answerService.findAnswerById(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Answer> addAnswer(@RequestBody Answer answer) {
        Answer newAnswer = answerService.addAnswer(answer);
        return new ResponseEntity<>(newAnswer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Answer> updateAnswer(@RequestBody Answer answer) {
        Answer updateAnswer = answerService.updateAnswer(answer);
        return new ResponseEntity<>(updateAnswer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") UUID id) {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
