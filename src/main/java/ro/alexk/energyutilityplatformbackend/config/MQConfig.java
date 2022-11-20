package ro.alexk.energyutilityplatformbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "mq")
public record MQConfig(
        String queue,
        String exchange,
        String routingKey
) {
}