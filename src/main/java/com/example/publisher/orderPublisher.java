package com.example.publisher;

import com.example.DTO.Order;
import com.example.DTO.orderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/placeOrder")
public class orderPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/")
  public String placeOrder(@RequestBody Order order) {

        orderStatus  orderStatus=new orderStatus(order , "Delivered" ," Order is Delivered");

        rabbitTemplate.convertAndSend("RabbitMQ_exchange","RabbitMQ_routingKey",orderStatus);
        return "Order Successfully Delivered";
  }

}
