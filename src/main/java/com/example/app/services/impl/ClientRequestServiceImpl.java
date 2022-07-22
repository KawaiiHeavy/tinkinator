package com.example.app.services.impl;

import com.example.app.dto.ClientRequestDTO;
import com.example.app.models.ClientRequest;
import com.example.app.repositories.ClientRequestRepository;
import com.example.app.services.ClientRequestService;
import com.example.app.utils.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class ClientRequestServiceImpl implements ClientRequestService {

    private ClientRequestRepository clientRequestRepository;
    private RabbitTemplate template;
    private Mapper mapper;

    public ClientRequestDTO addClientRequest(ClientRequestDTO clientRequestDTO) {

        log.info(String.format("Emit '%s'", clientRequestDTO.toString()));
        ClientRequest clientRequest = (ClientRequest) template.convertSendAndReceive("topic-exchange",
                "queue.clientrequests",
                mapper.mapToClientRequest(clientRequestDTO));
        log.info(String.format("Received on producer '%s'", clientRequest));

        return mapper.mapToClientRequestDTO(clientRequest);
    }

    public List<ClientRequestDTO> findAllClientRequests() {
        List<ClientRequestDTO> clientRequests = clientRequestRepository.findAll()
                .stream()
                .map(clientRequest -> mapper.mapToClientRequestDTO(clientRequest))
                .collect(Collectors.toList());
        return clientRequests;
    }
}
