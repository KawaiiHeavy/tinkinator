package com.example.app.repositories;

import com.example.app.models.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, UUID> {

    void deleteSolutionById(UUID id);
    Optional<Solution> findSolutionById(UUID id);
    Optional<Solution> findSolutionBySolutionText(String solutionText);
}
