package com.example.app.repositories;

import com.example.app.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    void deleteQuestionById(UUID id);

    Optional<Question> findQuestionById(UUID id);

}
