package com.example.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class SolutionDTO {

    private UUID id;
    private String solutionText;

}
