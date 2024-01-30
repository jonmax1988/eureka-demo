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

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    public Order selectOrderById(Integer id){
        return new Order(id,"order-001","上海长宁",31863.0,selectProductListByDiscoverClient());
    }
    //获取服务列表
    private List<Product> selectProductListByDiscoverClient(){
        StringBuffer sb=null;
        List<String> serviceIds=discoveryClient.getServices();
        if(CollectionUtils.isEmpty(serviceIds)){
            return null;
        }
        //根据服务名称获取服务
        List<ServiceInstance> serviceInstances=discoveryClient.getInstances("service-provider");
        if(CollectionUtils.isEmpty(serviceInstances)){
            return null;
        }
        ServiceInstance si=serviceInstances.get(0);
        sb=new StringBuffer();
        sb.append("http://"+si.getHost()+":"+si.getPort()+"/product/list");
        //封装返回数据
        ResponseEntity<List<Product>> response=restTemplate.exchange(
            sb.toString(),
                HttpMethod.GET,
               null,
            new ParameterizedTypeReference<List<Product>>(){});
        return response.getBody();
    }
}
