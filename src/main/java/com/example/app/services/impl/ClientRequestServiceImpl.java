package com.example.app.services.impl;

import com.example.app.dto.ClientRequestDTO;
import com.example.app.services.ClientRequestService;
import com.example.app.utils.Mapper;
import org.springframework.amqp.core.AmqpTemplate;
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

    public ClientRequestDTO addClientRequest(ClientRequestDTO clientRequest) {

        logger.info(String.format("Emit '%s'", clientRequest));
        template.convertAndSend("messages", mapper.mapToClientRequest(clientRequest));
        logger.info(String.format("Received on producer '%s'", clientRequest.toString()));

        return clientRequest;
    }
}
