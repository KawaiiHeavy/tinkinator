package com.example.app.services;

import com.example.app.dto.ClientRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ClientRequestService {

    ClientRequestDTO addClientRequest(ClientRequestDTO clientRequest);

}
