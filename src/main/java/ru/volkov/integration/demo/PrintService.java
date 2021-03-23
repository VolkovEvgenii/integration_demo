package ru.volkov.integration.demo;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;

public class PrintService {

    public void print(Message<String> message) {

        MessageHeaders headers = message.getHeaders();

        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            System.out.println("Header: key is - '{" + entry.getKey() + "}', value is value is - '{" + entry.getValue() + "}'");
        }

        System.out.println(message.getPayload());
    }
}
