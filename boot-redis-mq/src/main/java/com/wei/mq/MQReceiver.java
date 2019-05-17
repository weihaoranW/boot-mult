package com.wei.mq;

import com.wei.dto.RegisterDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.wei.config.MQConfig;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MQReceiver {

@RabbitListener(queues = MQConfig.QUEUE_TEST_STRING)
public void onReceiveString(String s) {
 log.info("##########onReceiveString received:" + s);
}

@RabbitListener(queues = MQConfig.QUEUE_CUSTOMER_REGISTER)
public void onCustomerRegister(RegisterDTO data) {
 log.info("########## onCustomerRegister received " + data.toString());
}

@RabbitListener(queues = MQConfig.QUEUE_CUSTOMER_FIND_PWD)
public void onCustomerFindPWD(RegisterDTO data) {
 log.info("##########onCustomerFindPWD received :" + data.toString());
}

}
