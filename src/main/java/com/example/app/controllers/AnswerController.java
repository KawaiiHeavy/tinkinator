package com.example.app.controllers;

import com.example.app.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

}