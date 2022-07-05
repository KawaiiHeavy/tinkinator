package com.example.app.repositories;

import com.example.app.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    void deleteAnswerById(UUID id);
    Optional<Answer> findAnswerById(UUID id);
    Optional<Answer> findAnswerByQuestionId(UUID id);

}
