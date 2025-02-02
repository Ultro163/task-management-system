package com.example.controller;

import com.example.service.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class TestController {
    private final KafkaSender sender;

    @PostMapping
    public void sendMessage(@RequestBody String message) {
        sender.sendMessage(message, "topic-1");
    }
}