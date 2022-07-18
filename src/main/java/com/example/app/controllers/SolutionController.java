package com.example.app.controllers;

import com.example.app.dto.SolutionDTO;
import com.example.app.models.Solution;
import com.example.app.services.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/solution")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @GetMapping("/all")
    public ResponseEntity<List<SolutionDTO>> getAllSolutions() {
        List<SolutionDTO> solutions = solutionService.findAllSolutions();
        return new ResponseEntity<>(solutions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SolutionDTO> getSolutionById(@PathVariable("id") UUID id) {
        SolutionDTO solution = solutionService.findSolutionById(id);
        return new ResponseEntity<>(solution, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<SolutionDTO> addSolution(@RequestBody SolutionDTO solution) {
        SolutionDTO newSolution = solutionService.addSolution(solution);
        return new ResponseEntity<>(newSolution, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<SolutionDTO> updateSolution(@RequestBody SolutionDTO solution) {
        SolutionDTO updateSolution = solutionService.updateSolution(solution);
        return new ResponseEntity<>(updateSolution, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSolution(@PathVariable("id") UUID id) {
        solutionService.deleteSolution(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}