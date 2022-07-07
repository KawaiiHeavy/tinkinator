package com.example.app.services;

import com.example.app.models.Solution;

import java.util.List;
import java.util.UUID;

public interface SolutionService {

    Solution addSolution(Solution solution);
    List<Solution> findAllSolutions();
    Solution updateSolution(Solution solution);
    Solution findSolutionById(UUID id);
    void deleteSolution(UUID id);

}
