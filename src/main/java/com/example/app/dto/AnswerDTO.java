package com.example.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AnswerDTO {

    private UUID id;
    private String answerText;
    private SolutionDTO solution;

}
