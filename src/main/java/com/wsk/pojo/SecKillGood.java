package com.wsk.pojo;

import java.util.Date;
public class SecKillGood {
    private int id;
    private Integer sId;//对应的商品iD
    private int count;
    private Date startDate;
    private Date endDate;
    private int display;//是否上架售卖

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "SecKIllGood{" +
                "id=" + id +
                ", sId=" + sId +
                ", count=" + count +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", display=" + display +
                '}';
    }
}
