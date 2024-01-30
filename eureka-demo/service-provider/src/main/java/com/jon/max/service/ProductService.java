package com.jon.max.service;

import com.jon.max.entity.Product;

import java.util.List;

/**
 * 商品服务的接口
 */
public interface ProductService {
    /**
     * 查询商品列表
     * @return 商品信息
     */
    List<Product> selectProductList();
}
