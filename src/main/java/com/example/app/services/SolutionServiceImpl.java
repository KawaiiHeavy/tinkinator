package com.example.app.services;

import com.example.app.dto.SolutionDTO;
import com.example.app.exceptions.SolutionNotFoundException;
import com.example.app.models.Solution;
import com.example.app.repositories.SolutionRepository;
import com.example.app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private Mapper mapper;

    public Solution addSolution(Solution solution) {
        solution.setId(UUID.randomUUID());
        return solutionRepository.save(solution);
    }

    public List<SolutionDTO> findAllSolutions() {
        List<SolutionDTO> solutionDTOList = new LinkedList<>();
        solutionRepository.findAll().stream().distinct().forEach(
                solution -> solutionDTOList.add(mapper.mapToSolutionDTO(solution))
        );
        return solutionDTOList;
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

}
