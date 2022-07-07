package com.example.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class QuestionDTO {

    private UUID id;
    private String questionText;
    private boolean isRoot;
    private Set<AnswerDTO> answers;


}
