package com.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class SpringRabbitListener {
    // 消费者1 和
    // 消费者2 都是订阅direct.queue1，它绑定到direct.exchange
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "direct.exchange", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenSimpleQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1 接收到的消息：【" + msg + "】");
        Thread.sleep(20);
    }

    @RabbitListener(queuesToDeclare = @Queue("direct.queue1"))
    public void listenSimpleQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2 接收到的消息：【" + msg + "】");
        Thread.sleep(100);
    }

    // 消费者3 是订阅direct.queue2，它绑定到direct.exchange
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "direct.exchange", type = ExchangeTypes.DIRECT),
            key = {"yellow", "blue"}
    ))
    public void listenSimpleQueue3(String msg) throws InterruptedException {
        System.out.println("消费者3 接收到的消息：【" + msg + "】");
        Thread.sleep(200);
    }

    // 消费者3 是订阅direct.queue3，但它没有绑定direct.exchange
    @RabbitListener(queuesToDeclare = @Queue("direct.queue3"))
    public void listenSimpleQueue4(String msg) throws InterruptedException {
        System.out.println("消费者4 接收到的消息：【" + msg + "】");
        Thread.sleep(200);
    }
}
