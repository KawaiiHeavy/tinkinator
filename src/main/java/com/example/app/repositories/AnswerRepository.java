package com.example.app.repositories;

import com.example.app.models.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, UUID> {
}
