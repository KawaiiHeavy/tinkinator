package com.example.app.services;

import com.example.app.dto.SolutionDTO;
import com.example.app.models.Solution;

import java.util.List;
import java.util.UUID;

public interface SolutionService {

    SolutionDTO addSolution(SolutionDTO solution);
    List<SolutionDTO> findAllSolutions();
    SolutionDTO updateSolution(SolutionDTO solution);
    SolutionDTO findSolutionById(UUID id);
    void deleteSolution(UUID id);

}
