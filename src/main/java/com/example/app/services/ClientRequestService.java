package com.example.app.services;

import com.example.app.dto.ClientRequestDTO;

import java.util.List;

public interface ClientRequestService {

    ClientRequestDTO addClientRequest(ClientRequestDTO clientRequest);
    List<ClientRequestDTO> findAllClientRequests();
}
