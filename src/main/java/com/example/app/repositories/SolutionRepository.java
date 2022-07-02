package com.example.app.repositories;

import com.example.app.models.Solution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolutionRepository extends CrudRepository<Solution, UUID> {
}
