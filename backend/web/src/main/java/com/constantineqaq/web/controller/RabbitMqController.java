package com.constantineqaq.web.controller;

import com.constantineqaq.base.constant.RabbitMqConstant;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class RabbitMqController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    @ResponseBody
    public String sendMessage() {
        String msgId = UUID.randomUUID().toString().replaceAll("-", "");
        rabbitTemplate.convertAndSend(
                RabbitMqConstant.EXCHANGE_NAME, RabbitMqConstant.ROUTING_KEY, "hello world" , message -> {
                    message.getMessageProperties().setMessageId(msgId);
                    return message;
                }
        );
        return "success";
    }
}
