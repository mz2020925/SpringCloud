package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.订单中包含用户的信息，需要向用户的微服务中请求获取，在spring中，RestTemplate是专门用于发起http请求
        // String url = "http://localhost:8081/user/" + order.getUserId();
        String url = "http://userservice/user/" + order.getUserId();
        // 3.向用户的微服务中请求获取用户信息，并返回信息封装到对象中
        User user = restTemplate.getForObject(url, User.class);
        // 4.将用户信息对象 赋值给 订单对象 包含用户的信息
        order.setUser(user);
        // 5.返回订单对象
        return order;
    }


}
