package me.dipper.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitConsumer {

    @RabbitListener(queues = {"chat1.queue", "chat2.queue"})
    public void consumeMessage(final RabbitMessage message) {
        log.info(message.getMessage());
    }
}
