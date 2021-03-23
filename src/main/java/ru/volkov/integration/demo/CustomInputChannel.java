package ru.volkov.integration.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;

@Configuration
public class CustomInputChannel {

    @Bean
    public QueueChannel inputChannel() {
        QueueChannel channel = new QueueChannel(5);
        return channel;
    }
}
