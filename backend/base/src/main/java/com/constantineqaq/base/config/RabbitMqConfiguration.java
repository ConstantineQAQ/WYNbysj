package com.constantineqaq.base.config;

import com.constantineqaq.base.constant.RabbitMqConstant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * @author wangyaning33
 */
@Configuration
@Slf4j
public class RabbitMqConfiguration {
    @Resource
    private CachingConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            String msgId = null;
            if (correlationData != null) {
                msgId = correlationData.getId();
            }
            if (ack) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})", msgId, ack, cause);
            } else {
                log.info("消息发送失败:correlationData({}),ack({}),cause({})", msgId, ack, cause);
            }
        });
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(returned -> {
            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", returned.getExchange(),
                    returned.getRoutingKey(), returned.getReplyCode(), returned.getReplyText(),
                    returned.getMessage());
        });
        return rabbitTemplate;
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMqConstant.EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue(RabbitMqConstant.QUEUE_NAME, true,false,false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }
}
