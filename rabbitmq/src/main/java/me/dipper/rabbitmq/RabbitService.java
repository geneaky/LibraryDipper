package me.dipper.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitService {

    private final RabbitMqProperty rabbitMqProperty;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessageToQueueThroughExchangeByRoutingKey(RabbitMessage message) {
        rabbitTemplate.convertAndSend(rabbitMqProperty.getExchangeName(),"",message);
        log.info("메시지보내기");
    }
}
