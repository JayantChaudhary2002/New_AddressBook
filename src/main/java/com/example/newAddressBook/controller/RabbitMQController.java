package com.example.newAddressBook.controller;

import com.example.newAddressBook.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rabbitmq")
public class RabbitMQController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        rabbitMQSender.sendMessage(message);
        return "Message sent: " + message;
    }
}
