package com.example.app.utils;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.QuestionDTO;
import com.example.app.dto.SolutionDTO;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JsonMapper {

    public JSONObject mapAnswerToJSON(AnswerDTO answerDTO) {
        JSONObject json = new JSONObject();
        json.put("id", answerDTO.getId());
        json.put("answerText", answerDTO.getAnswerText());

        SolutionDTO solution = answerDTO.getSolution();
        if (solution != null) {
            json.put("solution", mapSolutionToJSON(solution));
        }
        return json;
    }

    public JSONObject mapSolutionToJSON(SolutionDTO solutionDTO) {
        JSONObject json = new JSONObject();
        json.put("id", solutionDTO.getId());
        json.put("solutionText", solutionDTO.getSolutionText());
        return json;
    }

    public JSONObject mapToQuestionJSON(QuestionDTO questionDTO) {
        JSONObject json = new JSONObject();
        json.put("id", questionDTO.getId());
        json.put("questionText", questionDTO.getQuestionText());
        json.put("isRoot", questionDTO.isRoot());

        Set<AnswerDTO> answers = questionDTO.getAnswers();
        if (answers != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            answers.forEach(ans -> {
                sb.append(mapAnswerToJSON(ans));
                sb.append(",");
            });
            sb.append("]");
            json.put("answers", sb.toString());
        }
        return json;
    }
}
