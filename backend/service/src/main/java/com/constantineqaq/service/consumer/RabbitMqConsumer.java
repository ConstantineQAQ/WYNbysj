package com.constantineqaq.service.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.constantineqaq.base.constant.CanalConstant;
import com.constantineqaq.base.constant.RabbitMqConstant;
import com.constantineqaq.base.enums.CanalTypeEnum;
import com.constantineqaq.base.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangyaning33
 */
@Component
@Slf4j
public class RabbitMqConsumer {

    @RabbitListener(queues = {RabbitMqConstant.QUEUE_NAME})
    public void receiveMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("接收到来自Canal的消息:{}", msg);
        JSONObject jsonObject = JSON.parseObject(msg);
        String tableName = jsonObject.getString(CanalConstant.LISTEN_TABLE);
        if (!CanalConstant.TABLE_NAME.equalsIgnoreCase(tableName)) {
            log.warn("该数据不是来自student_main表，无需处理。该数据所在表：{}", tableName);
        }
        String type = jsonObject.getString(CanalConstant.LISTEN_TYPE);
        JSONArray data = jsonObject.getJSONArray(CanalConstant.LISTEN_DATA);
        CanalTypeEnum canalTypeEnum = CanalTypeEnum.getEnumByCode(type);
        if (canalTypeEnum == CanalTypeEnum.INSERT) {
            data.forEach(o -> {
                Person person = JSON.parseObject(o.toString(), Person.class);
                log.info("新增数据：{}", person);
            });
        }
    }
}
