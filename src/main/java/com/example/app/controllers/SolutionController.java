package com.example.app.controllers;

import com.example.app.dto.SolutionDTO;
import com.example.app.services.SolutionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/solution")
public class SolutionController {

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
        System.out.println(solution);
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

    @GetMapping("/countAll")
    public ResponseEntity<Integer> countAllSolutions() {
        Integer count = solutionService.countAllSolutions();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/allPageable")
    public ResponseEntity<Page<SolutionDTO>> getAllSolutionsPaging(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<SolutionDTO> solutionPage = solutionService.getAllSolutionsPaging(paging);
        return new ResponseEntity<>(solutionPage, HttpStatus.OK);
    }
}