package com.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class SpringRabbitListener {
    // 消费者1 是订阅topic.queue1，它绑定到topic.exchange
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = {"china.#"}
    ))
    public void listenSimpleQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1 接收到的消息：【" + msg + "】");
        Thread.sleep(20);
    }

    // 消费者2 是订阅topic.queue2，它绑定到topic.exchange
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    ))
    public void listenSimpleQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2 接收到的消息：【" + msg + "】");
        Thread.sleep(100);
    }

    // 消费者3 是订阅topic.queue3，它绑定到topic.exchange
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue3"),
            exchange = @Exchange(name = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = {"#.weather"}
    ))
    public void listenSimpleQueue3(String msg) throws InterruptedException {
        System.out.println("消费者3 接收到的消息：【" + msg + "】");
        Thread.sleep(200);
    }

    // 消费者4 是订阅topic.queue4，但它没有绑定topic.exchange
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue4"),
            exchange = @Exchange(name = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = {"US.#"}
    ))
    public void listenSimpleQueue4(String msg) throws InterruptedException {
        System.out.println("消费者4 接收到的消息：【" + msg + "】");
        Thread.sleep(200);
    }
}
