package ru.volkov.integration.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@Configuration
@EnableIntegration
public class DemoApplication implements ApplicationRunner {

    @Autowired
    private DirectChannel inputChannel;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Message<String> message = MessageBuilder
                .withPayload("Hello world from builder pattern!")
                .setHeader("newHeader", "newHeaderValue")
                .build();

        MessagingTemplate template = new MessagingTemplate();
        Message returnMessage = template.sendAndReceive(inputChannel, message);
        System.out.println(returnMessage.getPayload());

    }
}
