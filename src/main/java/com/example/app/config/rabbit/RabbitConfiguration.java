package com.example.app.config.rabbit;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Log4j2
@EnableRabbit
@Configuration
public class RabbitConfiguration {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    public Queue clientRequestsQueue() {
        return new Queue("queue.clientrequests");
    }

    @Bean
    public Queue totalQueue() {
        return new Queue("queue.totalQueue");
    }

    @Bean
    public Binding clientRequestsBinding(Queue clientRequestsQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(clientRequestsQueue).to(topicExchange).with("queue.clientrequests");
    }

    @Bean
    public Binding allBinding(Queue totalQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(totalQueue).to(topicExchange).with("queue.*");
    }
}