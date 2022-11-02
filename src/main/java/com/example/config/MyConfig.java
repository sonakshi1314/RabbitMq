package com.example.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyConfig {

    @Bean
    public Queue myQueue(){
         return new Queue("RabbitMQ_queue");
    }
    @Bean
    public TopicExchange myExchanger(){
        return new TopicExchange("RabbitMQ_exchange");
    }

    //bind exchange with queue
    @Bean
    public Binding bind(Queue myQueue , TopicExchange myExchanger){
      return   BindingBuilder.bind(myQueue).to(myExchanger).with("RabbitMQ_routingKey");
    }

    //MessageConverter --> we will be using objects not strings.
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    //rabbitMq template--> publish event yah message
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
