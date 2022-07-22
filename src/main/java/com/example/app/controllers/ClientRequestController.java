package com.example.app.controllers;


import com.example.app.dto.ClientRequestDTO;
import com.example.app.services.ClientRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

