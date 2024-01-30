package com.jon.max.service.impl;

import com.jon.max.entity.Product;
import com.jon.max.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> selectProductList() {
        return Arrays.asList(
                new Product(1,"华为手机",2,5800),
                new Product(2,"vivo手机",1,1800),
                new Product(3,"小米手机",5,4800)
        );
    }
}
