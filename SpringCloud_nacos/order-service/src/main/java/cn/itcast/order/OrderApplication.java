package cn.itcast.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
我们要去eureka-server中拉取user-service服务的实例列表，并且实现负载均衡。
不过这些动作不用我们去做，只需要添加一些注解即可。
 */

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class OrderApplication {
    // 现在需求：“订单微服务”返回的数据中应该包括用户信息，可是用户信息在“用户微服务”中，所以“订单微服务”需要像浏览器一样向“订单微服务”发起http请求，获取
    // 用户信息，如何实现这个需求？用RestTemplate类。springboot中所有的对象都要放到容器中作为Bean。
    @Bean
    @LoadBalanced // SpringCloud底层实现负载均衡其实是利用了一个名为Ribbon的组件，这个组件同时还会把我们发出的请求明明是http://userservice/user/1，变成http://localhost:8081
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}





/*
在服务的调用过程中，使用到了一个组件，叫做 RestTemplate，RestTemplate 是由 Spring 提供的一个 HTTP 请求工具。
其实开发者也可以不使用 RestTemplate ，使用 Java 自带的 HttpUrlConnection 或者经典的网络访问框架 HttpClient 也可以完成，只是在 Spring 项目中，使用 RestTemplate 显然更方便一些。
在传统的项目架构中，因为不涉及到服务之间的调用，大家对 RestTemplate 的使用可能比较少。

RestTemplate 简介：
RestTemplate 是从 Spring3.0 开始支持的一个 HTTP 请求工具，它提供了常见的REST请求方案的模版，
例如 GET 请求、POST 请求、PUT 请求、DELETE 请求以及一些通用的请求执行方法 exchange 以及 execute。
RestTemplate 继承自 InterceptingHttpAccessor 并且实现了 RestOperations 接口，其中 RestOperations 接口定义了基本的 RESTful 操作，这些操作在 RestTemplate 中都得到了实现。
————————————————
版权声明：本文为CSDN博主「52Babara」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/dqxiaoxiao/article/details/114375873

 */