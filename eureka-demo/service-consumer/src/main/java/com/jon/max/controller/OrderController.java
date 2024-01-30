package com.jon.max.controller;

import com.jon.max.pojo.Order;
import com.jon.max.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order selectOrderById(@PathVariable("id") Integer id){
        return orderService.selectOrderById(id);
    }
}
