package com.wei.receive;

import com.wei.config.MQDConfigTest1;
import com.wei.config.MQDConfigTestRegister;
import com.wei.dto.RegisterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * direct模式消费者
 *
 * @author cm_wang
 */
@Component
@Slf4j
public class DirectReceiverTestRegister {

// queues是指要监听的队列的名字
@RabbitListener(queues = MQDConfigTest1.QUEUE)
public void onReceived(String s) {
 log.info("############" + 1 + "----receiverDirectQueue收到消息----for whr---------" + s);
}

@RabbitListener(queues = MQDConfigTestRegister.QUEUE)
public void onReceived_register(RegisterDTO data) {
 log.info("***************************************");
 log.info("**********" + 2 + "----onReceived_register----for whr---------" + data.toString());
}

}
