package com.interviews.__demo.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class MessageConsumer {

    private final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    public MessageConsumer() {
    }

    @RabbitListener(queues = "${rabbitmq.queueName}")
    public void onMessage(String message) {
        logger.info("received new message {}", message);
    }
}