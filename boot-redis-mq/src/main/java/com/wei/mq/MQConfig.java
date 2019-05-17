package com.wei.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct模式
 *
 * @author cm_wang
 */
@Configuration
public class MQConfig {

public static final String QUEUE_TEST_STRING = "queue.test.string";
public static final String QUEUE_CUSTOMER_REGISTER = "queue.customer.register";
public static final String QUEUE_CUSTOMER_FIND_PWD = "queue.customer.findPWD";

@Bean
public Queue queueString() {
 // 第一个参数是队列名字， 第二个参数是指是否持久化
 return new Queue(QUEUE_TEST_STRING, true);
}

//
@Bean
public Queue queueCustomerRegister() {
 // 第一个参数是队列名字， 第二个参数是指是否持久化
 return new Queue(QUEUE_CUSTOMER_REGISTER, true);
}

@Bean
public Queue queueCustomerFindPWD() {
 // 第一个参数是队列名字， 第二个参数是指是否持久化
 return new Queue(QUEUE_CUSTOMER_FIND_PWD, true);
}

}
