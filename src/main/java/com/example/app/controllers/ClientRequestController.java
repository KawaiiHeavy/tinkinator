package com.example.app.controllers;


import com.example.app.dto.ClientRequestDTO;
import com.example.app.models.ClientRequest;
import com.example.app.services.ClientRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/clientRequest")
public class ClientRequestController {

    private ClientRequestService clientRequestService;

    @Autowired
    public ClientRequestController(ClientRequestService clientRequestService) {
        this.clientRequestService = clientRequestService;
    }

    @PostMapping("/add")
    public ResponseEntity<ClientRequestDTO> addClientRequest(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientRequestDTO clientRequest = clientRequestService.addClientRequest(clientRequestDTO);
        return new ResponseEntity<>(clientRequest, HttpStatus.OK);
    }

}


