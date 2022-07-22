package com.example.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ClientRequestDTO implements Serializable {

    @JsonProperty
    private UUID id;
    @JsonProperty
    private String requestText;

}
