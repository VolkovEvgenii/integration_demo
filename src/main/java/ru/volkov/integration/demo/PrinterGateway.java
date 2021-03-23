package ru.volkov.integration.demo;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@MessagingGateway
public interface PrinterGateway {

    @Gateway(requestChannel = "inputChannel")
    Future<Message<String>> print(Message<?> message);
}
