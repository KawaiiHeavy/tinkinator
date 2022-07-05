package com.example.app.services;

import com.example.app.dto.SolutionDTO;
import com.example.app.models.Solution;

import java.util.List;
import java.util.UUID;

public interface SolutionService {

    Solution addSolution(Solution solution);
    List<SolutionDTO> findAllSolutions();
    Solution updateSolution(Solution solution);
    Solution findSolutionById(UUID id);
    void deleteSolution(UUID id);

}
