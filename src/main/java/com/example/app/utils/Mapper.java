package com.example.app.utils;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.dto.SolutionDTO;
import com.example.app.models.Answer;
import com.example.app.models.Question;
import com.example.app.models.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Mapper {

    public AnswerDTO mapToAnswerDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setAnswerText(answer.getAnswerText());
        Solution solution = answer.getSolution();
        if (solution != null) {
            answerDTO.setSolution(mapToSolutionDTO(solution));
        }

        return answerDTO;
    }

    public Answer mapToAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        answer.setId(answerDTO.getId());
        answer.setAnswerText(answerDTO.getAnswerText());
        answer.setSolution(mapToSolution(answerDTO.getSolution()));

        return answer;
    }

    public Question mapToQuestion(QuestionDTO questionDTO){

        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setQuestionText(questionDTO.getQuestionText());
        question.setRoot(questionDTO.isRoot());

        Set<Answer> answers = questionDTO.getAnswers().stream()
                .collect(HashSet::new, (set, ans) -> set.add(mapToAnswer(ans)),
                        HashSet::addAll);

        question.setAnswers(answers);
        return question;
    }

    public QuestionDTO mapToQuestionDTO(Question question){

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setRoot(question.isRoot());
        Set<AnswerDTO> answers = question.getAnswers().stream()
                .collect(HashSet::new,
                        (set, ans) -> set.add(mapToAnswerDTO(ans)),
                        HashSet::addAll);
        questionDTO.setAnswers(answers);

        return questionDTO;
    }

    public List<QuestionDTO> mapToQuestionsDTO(List<Question> questions){

        return questions.stream()
                .collect(ArrayList::new,
                        (list, quest) -> list.add(mapToQuestionDTO(quest)),
                        ArrayList::addAll);
    }

    public SolutionDTO mapToSolutionDTO(Solution solution) {
        SolutionDTO solutionDTO = new SolutionDTO();
        solutionDTO.setId(solution.getId());
        solutionDTO.setSolutionText(solution.getSolutionText());

        return solutionDTO;
    }

    public Solution mapToSolution(SolutionDTO solutionDTO){
        Solution solution = new Solution();
        solution.setId(solutionDTO.getId());
        solution.setSolutionText(solutionDTO.getSolutionText());

        return solution;
    }

}
