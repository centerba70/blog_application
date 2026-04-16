package com.interviews.__demo.messaging;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessageBrokerConfig {

    private final String hostname;
    private final Integer port;
    private final String username;
    private final String password;
    private final String queueName;

    public MessageBrokerConfig(@Value("${rabbitmq.hostname}") String hostname,
                               @Value("${rabbitmq.port}") Integer port,
                               @Value("${rabbitmq.username}") String username,
                               @Value("${rabbitmq.password}") String password,
                               @Value("${rabbitmq.queueName}") String queueName) {
        this.hostname = hostname;
        this.port = port;
        this.queueName = queueName;
        this.username = username;
        this.password = password;
    }

    @Bean
    public Queue queue() {
       return new Queue(queueName, true);
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }

}