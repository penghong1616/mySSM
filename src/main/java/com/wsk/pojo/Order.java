package com.wsk.pojo;

import javafx.util.converter.IntegerStringConverter;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Integer id;
    private String orderName;
    private Integer userId;
    private String number;//订单号
    private Date createTime;
    private List<OrderDetail> orderDetails;
    private Integer status;
}
