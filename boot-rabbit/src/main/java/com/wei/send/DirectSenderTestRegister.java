package com.wei.send;

import com.wei.config.MQDConfigTestRegister;
import com.wei.dto.RegisterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DirectSenderTestRegister {

@Bean
public MappingJackson2MessageConverter jackson2Converter() {
 MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
 return converter;
}

@Autowired
private AmqpTemplate amqpTemplate;

public void sendDirect(RegisterDTO data) {
 //amqpTemplate.setMessageConverter(this.jackson2Converter);

 log.info("sendDirectQueue已发送消息");
 // 第一个参数是指要发送到哪个队列里面， 第二个参数是指要发送的内容
 for (int i = 0; i < 100; i++) {
  this.amqpTemplate.convertAndSend(MQDConfigTestRegister.QUEUE, data);
 }
}
}
