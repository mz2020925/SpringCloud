package com.mq.listener;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {
    // 1. 手动创建，需在RabbitMQ中手动创建myQueue1 队列，否则报错
//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue1(String msg) {
//        System.out.println("消费者接收到simple.queue的消息：【" + msg + "】");
//    }

    // 2. 自动创建队列
    @RabbitListener(queuesToDeclare = @Queue("basic.queue"))
    public void listenSimpleQueue2(String msg) {
        System.out.println("消费者接收到basic.queue的消息：【" + msg + "】");
    }
}
