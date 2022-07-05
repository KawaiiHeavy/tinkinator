package com.example.app.dto;

import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@NoArgsConstructor
@ToString
public class AnswerDTO {

    private UUID id;
    private String answerText;

}
