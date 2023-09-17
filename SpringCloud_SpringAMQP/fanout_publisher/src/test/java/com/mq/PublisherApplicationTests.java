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
        String exchangeName = "fanout.exchange";
        String message = "hello world";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(exchangeName, "","第" + i + "条消息：" + message);
            Thread.sleep(20);
        }
    }

}
