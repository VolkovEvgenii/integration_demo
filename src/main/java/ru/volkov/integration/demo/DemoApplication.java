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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@SpringBootApplication
@Configuration
@EnableIntegration
public class DemoApplication implements ApplicationRunner {

    @Autowired
    private PrinterGateway gateway;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Future<Message<String>>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Message<String> message = MessageBuilder
                    .withPayload("Printing message payload for - '{" + i + "}'")
                    .setHeader("messageNumber", i)
                    .build();

            System.out.println("Sending message - '{" + i + "}'");
            futures.add(this.gateway.print(message));
        }

        for (Future<Message<String>> future : futures) {
            System.out.println(future.get().getPayload());
        }
    }
}
