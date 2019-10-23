package com.wsk.pojo;

import lombok.Data;

@Data
public class OrderDetail {
    private Integer id;
    private Integer orderId;
    private Integer shopInfoId;
    private Integer shopInfoNum;
}
