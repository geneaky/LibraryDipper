package me.dipper.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/amqp/chat")
@RequiredArgsConstructor
public class RabbitController {

    private final RabbitService rabbitService;

    @Async
    @PostMapping
    public void sendMessage(@RequestBody RabbitMessage rabbitMessage) {
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                for(int j = 0; j < 100; j++) {
                    rabbitService.sendMessageToQueueThroughExchangeByRoutingKey(rabbitMessage);
                }
            }).start();
        }
    }
}
