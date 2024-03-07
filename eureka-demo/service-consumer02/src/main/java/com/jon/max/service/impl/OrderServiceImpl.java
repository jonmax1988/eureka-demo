package com.jon.max.service.impl;

import com.jon.max.pojo.Order;
import com.jon.max.pojo.Product;
import com.jon.max.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;
    //@Autowired
    //private DiscoveryClient discoveryClient;
    public Order selectOrderById(Integer id){
        return new Order(id,"order-001","上海长宁",31863.0,selectProductListByLoadBalancerAnnotation());
    }
    //获取服务列表
    private List<Product> selectProductListByLoadBalancerAnnotation(){
        ResponseEntity<List<Product>> response=restTemplate.exchange(
                "http://service-provider/product/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>(){});
        return response.getBody();
    }
}