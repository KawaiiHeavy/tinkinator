package com.example.app.services.impl;

import com.example.app.dto.ClientRequestDTO;
import com.example.app.models.ClientRequest;
import com.example.app.services.ClientRequestService;
import com.example.app.utils.Mapper;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class ClientRequestServiceImpl implements ClientRequestService {

    private Logger logger = Logger.getLogger(ClientRequestServiceImpl.class.getName());
    @Autowired
    RabbitTemplate template;
    @Autowired
    private Mapper mapper;

    public ClientRequestDTO addClientRequest(ClientRequestDTO clientRequestDTO) {

        logger.info(String.format("Emit '%s'", clientRequestDTO.toString()));
        ClientRequest clientRequest = (ClientRequest) template.convertSendAndReceive("messages",
                mapper.mapToClientRequest(clientRequestDTO));
        logger.info(String.format("Received on producer '%s'", clientRequest.toString()));

        return mapper.mapToClientRequestDTO(clientRequest);
    }
}
