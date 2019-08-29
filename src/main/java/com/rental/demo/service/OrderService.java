package com.rental.demo.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface OrderService {
    public int getOrderTotal(String username);

    public JSONObject getOrder(String username, String id, String type);

    public <T> List<T> getAllOrder(String username);
}
