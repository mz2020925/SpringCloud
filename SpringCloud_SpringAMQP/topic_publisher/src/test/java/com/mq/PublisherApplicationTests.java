package com.mq;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PublisherApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() throws InterruptedException {
        String exchangeName = "topic.exchange";
        String message = "hello world";
        rabbitTemplate.convertAndSend(exchangeName, "china.news","第 1 条消息：" + message + "， key = china.news");

    }

}
