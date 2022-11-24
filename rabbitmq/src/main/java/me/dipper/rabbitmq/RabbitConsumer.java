package me.dipper.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

    @RabbitListener(queues = "chat1.queue")
    public void consumeMessage(final RabbitMessage message) {
        System.out.println(message.getMessage());
    }
}
