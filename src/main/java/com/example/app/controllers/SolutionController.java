package com.example.app.controllers;

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
    public ResponseEntity<List<Solution>> getAllSolutions() {
        List<Solution> solutions = solutionService.findAllSolutions();
        return new ResponseEntity<>(solutions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Solution> getSolutionById(@PathVariable("id") UUID id) {
        Solution solution = solutionService.findSolutionById(id);
        return new ResponseEntity<>(solution, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Solution> addSolution(@RequestBody Solution solution) {
        Solution newSolution = solutionService.addSolution(solution);
        return new ResponseEntity<>(newSolution, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Solution> updateSolution(@RequestBody Solution solution) {
        Solution updateSolution = solutionService.updateSolution(solution);
        return new ResponseEntity<>(updateSolution, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSolution(@PathVariable("id") UUID id) {
        solutionService.deleteSolution(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
