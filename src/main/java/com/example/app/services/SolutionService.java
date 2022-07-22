package com.example.app.services;

import com.example.app.dto.SolutionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SolutionService {

    SolutionDTO addSolution(SolutionDTO solution);
    List<SolutionDTO> findAllSolutions();
    SolutionDTO updateSolution(SolutionDTO solution);
    SolutionDTO findSolutionById(UUID id);
    void deleteSolution(UUID id);
    Integer countAllSolutions();
    Page<SolutionDTO> getAllSolutionsPaging(Pageable paging);

}
