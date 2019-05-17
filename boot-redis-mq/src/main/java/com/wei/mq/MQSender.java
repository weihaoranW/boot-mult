package com.wei.mq;

import com.wei.config.MQConfig;
import com.wei.dto.RegisterDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * direct模式生产者
 *
 * @author cm_wang
 */
@Component
@Slf4j
public class MQSender {

@Autowired
private AmqpTemplate amqpTemplate;

public void sendString(String s) {
 log.info("sendString->已发送消息");
 this.amqpTemplate.convertAndSend(MQConfig.QUEUE_TEST_STRING,
  s);
}

public void sendCustomerRegister(RegisterDTO data) {
 log.info("sendCustomerRegister->已发送消息");
 this.amqpTemplate.convertAndSend(MQConfig.QUEUE_CUSTOMER_REGISTER,
  data);
}

public void sendCustomerFindPWD(RegisterDTO data) {
 log.info("sendCustomerFindPWD->已发送消息");
 this.amqpTemplate.convertAndSend(MQConfig.QUEUE_CUSTOMER_FIND_PWD,
  data);
}

}
