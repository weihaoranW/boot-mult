package com.wei.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic模式
 * 
 * @author cm_wang
 *
 */
@Configuration
public class RabbitMQTopicConfig {

	public static final String TOPIC_QUEUE = "topic.queue";
	
	public static final String TOPIC_EXCHANGE = "topic.exchange";

	@Bean
	public Queue topicQueue() {
		return new Queue(TOPIC_QUEUE);
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}

	@Bean
	public Binding topicBinding() {
		return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("rabbitmq.message");
	}
}
