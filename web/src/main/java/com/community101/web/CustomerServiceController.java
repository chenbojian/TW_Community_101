package com.community101.web;

import com.community101.core.*;
import com.community101.core.DTO.*;
import com.community101.core.service.CategoryService;
import com.community101.core.service.GoodsService;
import com.community101.core.service.OrdersService;
import com.community101.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerServiceController {
    private CategoryService categoryService;
    private GoodsService goodsService;
    private UserService userService;
    private OrdersService ordersService;

    @Autowired
    public CustomerServiceController(
            CategoryService categoryService,
            GoodsService goodsService,
            UserService userService,
            OrdersService ordersService) {
        this.categoryService = categoryService;
        this.goodsService = goodsService;
        this.userService = userService;
        this.ordersService = ordersService;
    }

    @RequestMapping("/categories")
    public List<CategoryDTO> listAllCategories() {
        List<CategoryDTO> categoryDTOList = Mapper.makeCategoryDTOList(categoryService.listCategory());

        return categoryDTOList;
    }

    @RequestMapping("/goods")
    public List<GoodsSummaryDTO> listAllGoodsOfCertainCategory(long cid) {
        if (cid == 0) {
            return listAllGoods();
        }

        List<GoodsSummaryDTO> goodsSummaryDTOList = null;
        Category category = categoryService.findCategoryById(cid);
        if (category != null) {
            goodsSummaryDTOList = Mapper.makeGoodsSummaryDTOList(category.getGoodses());
        }
        else {
            goodsSummaryDTOList = new LinkedList<GoodsSummaryDTO>();
        }

        return goodsSummaryDTOList;
    }

    @RequestMapping("/goods/all")
    public List<GoodsSummaryDTO> listAllGoods() {
        return Mapper.makeGoodsSummaryDTOList(goodsService.listGoods());
    }


    @RequestMapping("/goods/simple")
    public GoodsSummaryDTO getGoodsSimpleInformationById(long id) {
        GoodsSummaryDTO goodsSummaryDTO = null;
        Goods goods = goodsService.findGoodsById(id);
        if (goods != null) {
            goodsSummaryDTO = Mapper.makeGoodsSummayDTO(goods);
        }
        else {
            goodsSummaryDTO =new GoodsSummaryDTO(0,
                    "Not Found",
                    0,
                    "");
        }
        return goodsSummaryDTO;
    }

    @RequestMapping("goods/details")
    public GoodsDetailedDTO getGoodsGetailsById(long id) {
        GoodsDetailedDTO goodsDetailedDTO = null;
        Goods goods = goodsService.findGoodsById(id);
        if (goods != null) {
            goodsDetailedDTO = Mapper.makeGoodsDetailedDTO(goods);
        }
        else {
            goodsDetailedDTO = new GoodsDetailedDTO(0,
                    "Not Found",
                    0,
                    "",
                    "");
        }
        return goodsDetailedDTO;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long submitOrder(long[] ids, int[] quantities, String phone, String address) {
        Mapper mapper = new Mapper(userService, goodsService);
        Orders order = mapper.makeOrder(ids, quantities, phone, address);;

        ordersService.addOrder(order);
        return order.getId();
    }

}
