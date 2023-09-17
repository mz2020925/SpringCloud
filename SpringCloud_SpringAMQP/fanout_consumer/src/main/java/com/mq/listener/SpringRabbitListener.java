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
    // 消费者2 都是订阅fanout.queue1，它绑定到fanout.exchange
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue1"),
            exchange = @Exchange(name = "fanout.exchange", type = ExchangeTypes.FANOUT)
    ))
    public void listenSimpleQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1 接收到work.queue的消息：【" + msg + "】");
        Thread.sleep(20);
    }

    @RabbitListener(queuesToDeclare = @Queue("fanout.queue1"))
    public void listenSimpleQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2 接收到work.queue的消息：【" + msg + "】");
        Thread.sleep(100);
    }

    // 消费者3 是订阅fanout.queue2，它绑定到fanout.exchange
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue2"),
            exchange = @Exchange(name = "fanout.exchange", type = ExchangeTypes.FANOUT)
    ))
    public void listenSimpleQueue3(String msg) throws InterruptedException {
        System.out.println("消费者3 接收到work.queue的消息：【" + msg + "】");
        Thread.sleep(200);
    }

    // 消费者3 是订阅fanout.queue2，但它没有绑定到fanout.exchange
    @RabbitListener(queuesToDeclare = @Queue("fanout.queue3"))
    public void listenSimpleQueue4(String msg) throws InterruptedException {
        System.out.println("消费者4 接收到work.queue的消息：【" + msg + "】");
        Thread.sleep(200);
    }
}
