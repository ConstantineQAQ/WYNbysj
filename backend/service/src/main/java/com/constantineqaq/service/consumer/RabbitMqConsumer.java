package com.constantineqaq.service.consumer;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.constantineqaq.base.config.RabbitMqConfiguration;
import com.constantineqaq.base.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMqConsumer {

    @RabbitListener(queues = {RabbitMqConstant.QUEUE_NAME})
    public void receiveMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("接收到消息:{}", msg);
        JSONObject jsonObject = JSON.parseObject(msg);
    }
}
