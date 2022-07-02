package com.example.app.controllers;

import com.example.app.services.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/solutions")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

}
