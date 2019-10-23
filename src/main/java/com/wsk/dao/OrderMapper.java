package com.wsk.dao;

import com.wsk.pojo.Order;
import com.wsk.pojo.OrderDetail;

import java.util.List;

public interface OrderMapper {
    public int insertOrder(Order order);
    public List<OrderDetail> getOrderDetails(int id);
    public int updateStatus(int id,int status);
}
