package com.example.app.repositories;

import com.example.app.models.question.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, UUID> {

    @Query("select qa.answerId from QuestionAnswer qa where qa.questionId=:id")
    Set<UUID> findAllAnswersById(UUID id);

    @Query("select qa from QuestionAnswer qa where qa.questionId=:questionId")
    Set<QuestionAnswer> findAllByQuestionId(UUID questionId);

    @Query("select qa from QuestionAnswer qa where qa.questionId=:questionId and qa.answerId=:answerId")
    Optional<QuestionAnswer> findByQuestionAndAnswer(UUID questionId, UUID answerId);

}
