package com.example.app.services.impl;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.exceptions.QuestionNotFoundException;
import com.example.app.models.other.Answer;
import com.example.app.models.question.Question;
import com.example.app.models.other.Solution;
import com.example.app.models.question.QuestionAnswer;
import com.example.app.repositories.AnswerRepository;
import com.example.app.repositories.QuestionAnswerRepository;
import com.example.app.repositories.QuestionRepository;
import com.example.app.repositories.SolutionRepository;
import com.example.app.services.QuestionService;
import com.example.app.utils.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private AnswerRepository answerRepository;
    private QuestionAnswerRepository questionAnswerRepository;
    private SolutionRepository solutionRepository;
    private QuestionRepository questionRepository;
    private Mapper mapper;

    public QuestionDTO addQuestion(QuestionDTO questionDTO) {

        Question question = mapper.mapToQuestion(questionDTO);

        Set<Answer> answers = questionDTO.getAnswers()
                .stream()
                .map(mapper::mapToAnswer)
                .collect(Collectors.toSet());
        Set<Answer> answersFromDB = new HashSet<>();

        answers.forEach(answer -> {
            Solution solutionFromAns = answer.getSolution();

            Optional<Solution> solution = solutionRepository
                    .findSolutionBySolutionText(solutionFromAns.getSolutionText());
            solution.ifPresent(answer::setSolution);
            answersFromDB.add(answerRepository.save(answer));
        });
        Question questionFromDB = questionRepository.save(question);
        answersFromDB.forEach(answer -> {
            questionAnswerRepository.findByQuestionAndAnswer(questionFromDB.getId(), answer.getId()).ifPresentOrElse(null, () -> {
                QuestionAnswer questionAnswer = new QuestionAnswer();
                questionAnswer.setQuestionId(questionFromDB.getId());
                questionAnswer.setAnswerId(answer.getId());
                questionAnswerRepository.save(questionAnswer);
            });
        });
        return mapper.mapToQuestionDTO(questionFromDB, answersFromDB);
    }

    public List<QuestionDTO> findAllQuestions() {

        return questionRepository.findAll()
                .stream()
                .map(quest -> {
                    Set<Answer> answers = new HashSet<>();
                    questionAnswerRepository.findAllAnswersById(quest.getId()).forEach(answerId -> {
                        answers.add(answerRepository.getById(answerId));
                    });
                    return mapper.mapToQuestionDTO(quest, answers);
                })
                .collect(Collectors.toList());
    }

    public Page<QuestionDTO> getAllQuestionsPaging(Pageable paging) {
        return questionRepository.findAll(paging)
                .map(quest -> {
                    Set<Answer> answers = new HashSet<>();
                    questionAnswerRepository.findAllAnswersById(quest.getId()).forEach(answerId -> {
                        answers.add(answerRepository.getById(answerId));
                    });
                    return mapper.mapToQuestionDTO(quest, answers);
                });
    }

    public QuestionDTO updateQuestion(QuestionDTO question) {
        Set<Answer> answers = new HashSet<>();
        question.getAnswers().forEach(answerDTO -> {
            questionAnswerRepository.findAllAnswersById(question.getId()).forEach(answerId -> {
                questionAnswerRepository.findByQuestionAndAnswer(question.getId(), answerId).ifPresentOrElse(System.out::println, () -> {
                    QuestionAnswer questionAnswer = new QuestionAnswer();
                    questionAnswer.setQuestionId(question.getId());
                    questionAnswer.setAnswerId(answerId);
                    questionAnswerRepository.save(questionAnswer);
                });
            });
            answers.add(answerRepository.save(mapper.mapToAnswer(answerDTO)));
        });
        return mapper.mapToQuestionDTO(questionRepository.save(mapper.mapToQuestion(question)), answers);
    }

    public QuestionDTO findQuestionById(UUID id) {
        Set<Answer> answers = new HashSet<>();
        questionAnswerRepository.findAllAnswersById(id).forEach(answerId -> {
            answers.add(answerRepository.getById(answerId));
        });
        return mapper.mapToQuestionDTO(questionRepository.findQuestionById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question by id " + id + " was not found")), answers);
    }

    @Transactional
    public void deleteQuestion(UUID id) {
        questionAnswerRepository.findAllByQuestionId(id).forEach(questionAnswerRepository::delete);
        questionRepository.deleteById(id);
    }
}
