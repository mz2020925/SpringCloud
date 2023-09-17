package com.mq.listener;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class SpringRabbitListener {
    @RabbitListener(queuesToDeclare = @Queue("work.queue"))
    public void listenSimpleQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1 接收到work.queue的消息：【" + msg + "】");
        Thread.sleep(20);
    }

    @RabbitListener(queuesToDeclare = @Queue("work.queue"))
    public void listenSimpleQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2 接收到work.queue的消息：【" + msg + "】");
        Thread.sleep(200);
    }
}
