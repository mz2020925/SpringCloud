package cn.itcast.order.service;

import cn.itcast.order.pojo.Order;

public interface OrderService {
    public Order queryOrderById(Long orderId);
}
