package com.example.app.services;

import com.example.app.exceptions.SolutionNotFoundException;
import com.example.app.models.Solution;
import com.example.app.models.Solution;
import com.example.app.repositories.SolutionRepository;
import com.example.app.repositories.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    public Solution addSolution(Solution solution) {
        return solutionRepository.save(solution);
    }

    public List<Solution> findAllSolutions() {
        return solutionRepository.findAll();
    }

    public Solution updateSolution(Solution solution) {
        return solutionRepository.save(solution);
    }

    public Solution findSolutionById(UUID id) {
        return solutionRepository.findSolutionById(id)
                .orElseThrow(() -> new SolutionNotFoundException("Solution by id " + id + " was not found"));
    }

    @Transactional
    public void deleteSolution(UUID id) {
        solutionRepository.deleteSolutionById(id);
    }

    @Transactional
    public void deleteSolutions(List<UUID> ids){
        solutionRepository.deleteAllById(ids);
    }
}
