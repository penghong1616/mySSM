package com.wsk.dao;

import com.wsk.bean.SecKillGoodBean;
import com.wsk.pojo.SecKillCar;
import com.wsk.pojo.SecKillGood;

import java.util.List;

public interface SecKillGoodMapper {
    //查询所有的秒杀商品
    public List<SecKillGoodBean> selectAll();

    public SecKillGoodBean selectSecKillById(int id);

    public void insert(SecKillCar secKillCar);
    public int selectGoodById(int id);//根据id查询sId
}
