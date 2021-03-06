package com.example.app.controllers;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.dto.SolutionDTO;
import com.example.app.services.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/answer")
public class AnswerController {

    private AnswerService answerService;

    @GetMapping("/all")
    public ResponseEntity<List<AnswerDTO>> getAllAnswers() {
        List<AnswerDTO> answers = answerService.findAllAnswers();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable("id") UUID id) {
        AnswerDTO answer = answerService.findAnswerById(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AnswerDTO> addAnswer(@RequestBody AnswerDTO answer) {
        AnswerDTO newAnswer = answerService.addAnswer(answer);
        return new ResponseEntity<>(newAnswer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AnswerDTO> updateAnswer(@RequestBody AnswerDTO answer) {
        AnswerDTO updateAnswer = answerService.updateAnswer(answer);
        return new ResponseEntity<>(updateAnswer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") UUID id) {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findQuestionByAnswerId/{id}")
    public ResponseEntity<QuestionDTO> getQuestionByAnswerId(@PathVariable("id") UUID id){
        QuestionDTO question = answerService.findQuestionByAnswerId(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/findSolutionByAnswerId/{id}")
    public ResponseEntity<SolutionDTO> getSolutionByAnswerId(@PathVariable("id") UUID id){
        SolutionDTO solution = answerService.findSolutionByAnswerId(id);
        return new ResponseEntity<>(solution, HttpStatus.OK);
    }

    @GetMapping("/attachQuestion/{questionId}/{answerId}")
    public ResponseEntity<?> attachQuestion(@PathVariable("questionId") UUID questionId,
                                            @PathVariable("answerId") UUID answerId) {
        answerService.attachQuestion(questionId, answerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/countAll")
    public ResponseEntity<Integer> countAllAnswers() {
        Integer count = answerService.countAllAnswers();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/allPageable")
    public ResponseEntity<Page<AnswerDTO>> getAllAnswersPaging(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<AnswerDTO> answerPage = answerService.getAllAnswersPaging(paging);
        return new ResponseEntity<>(answerPage, HttpStatus.OK);
    }
}
