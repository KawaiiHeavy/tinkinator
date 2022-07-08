package com.example.app.dto;

import com.example.app.models.Solution;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AnswerDTO {

    private UUID id;
    private String answerText;
    private Solution solution;

}
