package com.example.app.rabbit;

import com.example.app.dto.ClientRequestDTO;
import com.example.app.models.ClientRequest;
import com.example.app.repositories.ClientRequestRepository;
import com.example.app.utils.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
public class RabbitMqListener {

    @Autowired
    private Mapper mapper;
    @Autowired
    private ClientRequestRepository clientRequestRepository;

    Logger logger = Logger.getLogger(RabbitMqListener.class.getName());

    @RabbitListener(queues = "messages")
    public ClientRequest worker1(ClientRequest clientRequest) {
        logger.info("Received on worker : " + clientRequest.toString());
        return clientRequestRepository.save(clientRequest);
    }

}
