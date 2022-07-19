package com.example.app.services.impl;

import com.example.app.dto.SolutionDTO;
import com.example.app.exceptions.SolutionNotFoundException;
import com.example.app.models.Answer;
import com.example.app.models.Solution;
import com.example.app.repositories.AnswerRepository;
import com.example.app.repositories.SolutionRepository;
import com.example.app.services.SolutionService;
import com.example.app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private Mapper mapper;

    public SolutionDTO addSolution(SolutionDTO solution) {
        return mapper.mapToSolutionDTO(solutionRepository.save(mapper.mapToSolution(solution)));
    }

    public List<SolutionDTO> findAllSolutions() {
        List<SolutionDTO> solutions = new LinkedList<>();
        solutionRepository.findAll().forEach(solution
                -> solutions.add(mapper.mapToSolutionDTO(solution)));
        return solutions;
    }

    public SolutionDTO updateSolution(SolutionDTO solution) {
        return mapper.mapToSolutionDTO(solutionRepository.save(mapper.mapToSolution(solution)));
    }

    public SolutionDTO findSolutionById(UUID id) {
        return mapper.mapToSolutionDTO(solutionRepository.findSolutionById(id)
                .orElseThrow(() -> new SolutionNotFoundException("Solution by id " + id + " was not found")));
    }

    @Transactional
    public void deleteSolution(UUID id) {
        List<Answer> answers = answerRepository.findAnswersBySolutionId(id);
        answers.forEach(answer -> answer.setSolution(null));
        solutionRepository.deleteSolutionById(id);
    }
}