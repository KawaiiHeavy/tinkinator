package com.example.app.repositories;

import com.example.app.models.Answer;
import com.example.app.models.Question;
import com.example.app.models.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    void deleteAnswerById(UUID id);
    Optional<Answer> findAnswerById(UUID id);

    @Query("select question from Answer a where a.id=:id")
    Optional<Question> findQuestionByAnswerId(UUID id);

    @Query("select a from Answer a where a.solution.id=:id")
    List<Answer> findAnswersBySolutionId(UUID id);

    @Query("select a from Answer a where a.question.id=:id")
    List<Answer> findAnswersByQuestionId(UUID id);

    @Query("select solution from Answer a where a.id=:id")
    Optional<Solution> findSolutionByAnswerId(UUID id);

    @Transactional
    @Modifying
    @Query("update Answer a set a.question=:question where a.id=:answerId")
    Optional<?> addQuestionToAnswer(Question question, UUID answerId);

}
