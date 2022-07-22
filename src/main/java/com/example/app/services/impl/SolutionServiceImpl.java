package com.example.app.services.impl;

import com.example.app.dto.SolutionDTO;
import com.example.app.exceptions.SolutionNotFoundException;
import com.example.app.models.Answer;
import com.example.app.models.Solution;
import com.example.app.repositories.AnswerRepository;
import com.example.app.repositories.SolutionRepository;
import com.example.app.services.SolutionService;
import com.example.app.utils.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@AllArgsConstructor
public class SolutionServiceImpl implements SolutionService {

    private AnswerRepository answerRepository;
    private SolutionRepository solutionRepository;
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

    public Integer countAllSolutions() {
        return solutionRepository.countAllByIdIsNotNull();
    }

    public Page<SolutionDTO> getAllSolutionsPaging(Pageable paging) {
        Page<Solution> page = solutionRepository.findAll(paging);
        return page.map(solution -> mapper.mapToSolutionDTO(solution));
    }

    @Transactional
    public void deleteSolution(UUID id) {
        List<Answer> answers = answerRepository.findAnswersBySolutionId(id);
        answers.forEach(answer -> answer.setSolution(null));
        solutionRepository.deleteSolutionById(id);
    }
}