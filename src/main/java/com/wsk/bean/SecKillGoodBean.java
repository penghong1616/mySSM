package com.wsk.bean;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀类和商品信息的冗余类
 */
@Data
public class SecKillGoodBean {
    private int id;
    private Integer sId;//对应的商品iD
    private int count;
    private Date startDate;
    private Date endDate;
    private int display;//是否上架售卖
    private Date modified;
    private String name;
    private Integer level;
    private String remark;
    private BigDecimal price;
    private Integer sort;
    private Integer quantity;
    private Integer transaction;
    private Integer uid;
    private String image;
    private Integer sales;
    private String thumbnails;
}
