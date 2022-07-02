package com.example.app.services;

import com.example.app.models.Solution;
import com.example.app.repositories.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    public Optional<Solution> findById(UUID id){
        return solutionRepository.findById(id);
    }

}
