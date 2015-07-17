package com.community101.core.service;

import com.community101.core.Goods;
import com.community101.core.dao.GoodsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenbojian on 7/17/15.
 */
@Service
public class GoodsService {

    private GoodsDAO goodsDAO;

    @Autowired
    public GoodsService(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    public void save(Goods goods) {
        goodsDAO.save(goods);
    }
}
