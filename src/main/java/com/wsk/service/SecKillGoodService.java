package com.wsk.service;

import com.wsk.bean.SecKillGoodBean;
import com.wsk.pojo.SecKillCar;

import java.util.List;

public interface SecKillGoodService {
    public List<SecKillGoodBean> selectAll();

    public SecKillGoodBean selectSecKillById(int id);

    public void insert(SecKillCar secKillCar);
    public int selectGoodById(int id);
}
