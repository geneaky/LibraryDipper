package me.dipper.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMqProperty {

    private final String host;
    private final Integer port;
    private final String username;
    private final String password;
    private final String exchangeName;
    private final String queueName;
}

