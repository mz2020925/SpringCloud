package cn.itcast.order.service.impl;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.订单中包含用户的信息，需要向用户的微服务中请求获取，用feign发起http请求
        // 3.向用户的微服务中请求获取用户信息，并返回信息封装到对象中
        User user = userClient.findById(order.getUserId());
        // 4.将用户信息对象 赋值给 订单对象 包含用户的信息
        order.setUser(user);
        // 5.返回订单对象
        return order;
    }
}
