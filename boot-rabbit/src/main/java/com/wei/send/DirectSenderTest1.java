package com.wei.send;

import com.alibaba.fastjson.JSONObject;
import com.wei.config.MQDConfigTest1;
import com.wei.config.RabbitMQDirectConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * direct模式生产者
 *
 * @author cm_wang
 */
@Component
@Slf4j
public class DirectSenderTest1 {

@Autowired
private AmqpTemplate amqpTemplate;

public void sendDirect(String s) {
 log.info("sendDirectQueue已发送消息");
 // 第一个参数是指要发送到哪个队列里面， 第二个参数是指要发送的内容
 for (int i = 0; i < 100; i++) {
  this.amqpTemplate.convertAndSend(MQDConfigTest1.QUEUE, s + "-<<<<--" + i + "-->>>");
 }
}
}
