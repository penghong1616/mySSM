package com.wsk.service;

import com.wsk.pojo.Order;

public interface OrderService {
    public  void insertOrder(Order order);
    public int update(int id,int status);
}
