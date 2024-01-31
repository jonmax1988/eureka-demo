package com.jon.max.service;

import com.jon.max.pojo.Order;

public interface OrderService {
    /**
     * 根据订单ID查询订单信息
     * @param id
     * @return
     */
    Order selectOrderById(Integer id);
}
