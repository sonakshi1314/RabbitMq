package com.example.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.DTO.orderStatus;

@Component
public class User {

        @RabbitListener(queues = "RabbitMQ_queue")
        public void consumeMessageFromQueue(orderStatus orderStatus) {
            System.out.println("Message received from queue : " + orderStatus);
        }

}
