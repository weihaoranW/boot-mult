package com.wei.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQDConfigTestRegister {

public static final String QUEUE = "register-user";
//public static final String EXCHANGER = "live-exchange";

//@Bean
//public DirectExchange exchanger() {
// return new DirectExchange(EXCHANGER);
//}

@Bean
public Queue register_user() {
 // 第一个参数是队列名字， 第二个参数是指是否持久化
 return new Queue(QUEUE, true);
}

//@Bean
//public Binding xbinding() {
//
// return BindingBuilder.bind(queue())
//         .to(exchanger()).with(QUEUE);
//}

}
