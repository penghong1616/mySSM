package com.wsk.service.Impl;

import com.wsk.bean.SecKillGoodBean;
import com.wsk.dao.SecKillGoodMapper;
import com.wsk.pojo.SecKillCar;
import com.wsk.service.SecKillGoodService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SecKillGoodServiceImpl implements SecKillGoodService {
    @Autowired
    private SecKillGoodMapper secKillGoodMapper;
    public List<SecKillGoodBean> selectAll() {
        return secKillGoodMapper.selectAll();
    }

    @Override
    public SecKillGoodBean selectSecKillById(int id) {
        return secKillGoodMapper.selectSecKillById(id);
    }

    @Override
    public void insert(SecKillCar secKillCar) {
        secKillGoodMapper.insert(secKillCar);
    }

    @Override
    public int selectGoodById(int id) {
        return secKillGoodMapper.selectGoodById(id);
    }
}
