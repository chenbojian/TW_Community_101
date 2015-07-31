package com.community101.admin.api;

import com.community101.core.Goods;
import com.community101.core.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chenbojian on 7/31/15.
 */

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Goods> listAll() {
        return goodsService.listGoods();
    }
}
