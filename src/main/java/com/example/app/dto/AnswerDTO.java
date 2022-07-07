package com.example.app.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AnswerDTO {

    private UUID id;
    private String answerText;

}
