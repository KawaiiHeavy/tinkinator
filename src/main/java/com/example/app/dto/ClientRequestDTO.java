package com.example.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ClientRequestDTO {

    private UUID id;
    private String requestText;

}
