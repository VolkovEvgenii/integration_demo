package ru.volkov.integration.demo;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrintService {

    @ServiceActivator(inputChannel = "inputChannel")
    public Message<?> print(Message<String> message) {

        MessageHeaders headers = message.getHeaders();

        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            System.out.println("Header: key is - '{" + entry.getKey() + "}', value is value is - '{" + entry.getValue() + "}'");
        }

        System.out.println(message.getPayload());

        return MessageBuilder.withPayload("new Message").build();
    }
}
