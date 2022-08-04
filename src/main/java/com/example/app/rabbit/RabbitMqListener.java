package com.example.app.rabbit;

import com.example.app.models.other.ClientRequest;
import com.example.app.repositories.ClientRequestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class RabbitMqListener {

    private ClientRequestRepository clientRequestRepository;

    @RabbitListener(queues = "queue.clientrequests")
    public ClientRequest worker1(ClientRequest clientRequest) {
        log.info("Received on worker : " + clientRequest.toString());
        return clientRequestRepository.save(clientRequest);
    }

}
