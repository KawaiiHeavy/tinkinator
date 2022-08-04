package com.example.app.services.impl;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.dto.SolutionDTO;
import com.example.app.exceptions.AnswerNotFoundException;
import com.example.app.exceptions.QuestionNotFoundException;
import com.example.app.exceptions.SolutionNotFoundException;
import com.example.app.models.other.Answer;
import com.example.app.models.question.Question;
import com.example.app.models.other.Solution;
import com.example.app.repositories.AnswerRepository;
import com.example.app.repositories.QuestionAnswerRepository;
import com.example.app.repositories.QuestionRepository;
import com.example.app.services.AnswerService;
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
public class AnswerServiceImpl implements AnswerService {

    private QuestionAnswerRepository questionAnswerRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private Mapper mapper;

    public AnswerDTO addAnswer(AnswerDTO answer) {
        return mapper.mapToAnswerDTO(answerRepository.save(mapper.mapToAnswer(answer)));
    }

    public List<AnswerDTO> findAllAnswers() {
        return answerRepository.findAll()
                .stream()
                .map(mapper::mapToAnswerDTO)
                .collect(Collectors.toList());
    }

    public AnswerDTO updateAnswer(AnswerDTO answer) {
        return mapper.mapToAnswerDTO(answerRepository.save(mapper.mapToAnswer(answer)));
    }

    public AnswerDTO findAnswerById(UUID id) {
        return mapper.mapToAnswerDTO(answerRepository.findAnswerById(id)
                .orElseThrow(() -> new AnswerNotFoundException("Answer by id " + id + " was not found")));
    }

    public QuestionDTO findQuestionByAnswerId(UUID id) {
        QuestionDTO questionDTO = null;
        Set<Answer> answers = new HashSet<>();
        Optional<UUID> question_id = answerRepository.findQuestionByAnswerId(id);
        if (question_id.isPresent()){
            Optional<Question> question = questionRepository.findById(question_id.get());
            questionAnswerRepository.findAllAnswersById(question_id.get()).forEach(answerId -> answers.add(answerRepository.getById(answerId)));
            questionDTO = mapper.mapToQuestionDTO(question.orElseThrow(()
                    -> new QuestionNotFoundException("Question by answerId " + id + " was not found")), answers);
        }
        return questionDTO;
    }

    public SolutionDTO findSolutionByAnswerId(UUID id) {
        Optional<Solution> solution = answerRepository.findSolutionByAnswerId(id);
        return mapper.mapToSolutionDTO(solution.orElseThrow(()
                -> new SolutionNotFoundException("Solution by answerId " + id + " was not found")));
    }

    public void attachQuestion(UUID questionId, UUID answerId) {
        Optional<Question> question = questionRepository.findById(questionId);
        question.ifPresent(quest -> {
            answerRepository.addQuestionToAnswer(quest.getId(), answerId);
        });
    }

    public Integer countAllAnswers() {
        return answerRepository.countAllByIdIsNotNull();
    }
    
    public Page<AnswerDTO> getAllAnswersPaging(Pageable paging) {
        Page<Answer> page = answerRepository.findAll(paging);
        return page.map(answer -> mapper.mapToAnswerDTO(answer));
    }

    @Transactional
    public void deleteAnswer(UUID id) {
        Optional<Answer> optionalAnswer = answerRepository.findAnswerById(id);
        optionalAnswer.ifPresent(answer -> {
            System.out.println(answer);

//            for (Question question : questionRepository.findAll()) {
//                Set<Answer> answers = question.getAnswers();
//                if (answers.contains(answer)) {
//                    answers.remove(answer);
//                }
//            }

            if (answer.getSolution() != null) {
                answer.setSolution(null);
            }

            answerRepository.delete(answer);
        });
    }
}
