package com.wsk.service.Impl;

import com.wsk.dao.OrderMapper;
import com.wsk.pojo.Order;
import com.wsk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    @Override
    public int update(int id, int status) {
        return orderMapper.updateStatus(id,status);
    }
}
