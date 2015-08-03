package com.community101.admin;

import com.community101.core.*;
import com.community101.admin.DTO.*;
import com.community101.core.service.GoodsService;
import com.community101.core.service.UserService;

import java.util.*;

/**
 * Created by MiffyLiye on 22/07/2015.
 */
public class Mapper {
    private UserService userService;
    private GoodsService goodsService;

    public Mapper(UserService userService,
                  GoodsService goodsService){
        this.userService = userService;
        this.goodsService = goodsService;
    }

    static public CategoryDTO makeCategoryDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }

    static public List<CategoryDTO> makeCategoryDTOList(List<Category> categoryList) {
        List<CategoryDTO> categoryDTOList = new LinkedList<CategoryDTO>();
        for (Category category : categoryList) {
            CategoryDTO categoryDTO = Mapper.makeCategoryDTO(category);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    static public GoodsSummaryDTO makeGoodsSummayDTO(Goods goods) {
        return new GoodsSummaryDTO(goods.getId(),
                goods.getName(),
                goods.getPrice(),
                goods.getPictureUrl());
    }

    static public List<GoodsSummaryDTO> makeGoodsSummaryDTOList(List<Goods> goodsList) {
        List<GoodsSummaryDTO> goodsSummaryDTOList = new LinkedList<GoodsSummaryDTO>();
        for (Goods goods : goodsList) {
            GoodsSummaryDTO goodsSummaryDTO = Mapper.makeGoodsSummayDTO(goods);
            goodsSummaryDTOList.add(goodsSummaryDTO);
        }
        return goodsSummaryDTOList;
    }

    static public List<GoodsSummaryDTO> makeGoodsSummaryDTOList(Set<Goods> goodsSet) {
        List<GoodsSummaryDTO> goodsSummaryDTOList = new LinkedList<GoodsSummaryDTO>();
        for (Goods goods : goodsSet) {
            GoodsSummaryDTO goodsSummaryDTO = Mapper.makeGoodsSummayDTO(goods);
            goodsSummaryDTOList.add(goodsSummaryDTO);
        }
        return goodsSummaryDTOList;
    }

    static public GoodsDetailedDTO makeGoodsDetailedDTO(Goods goods) {
        return new GoodsDetailedDTO(goods.getId(),
                goods.getName(),
                goods.getPrice(),
                goods.getPictureUrl(),
                goods.getDescription());
    }

    public static OrderDetailDTO makeOrderDetailDTO(Orders order){
        OrderDetailDTO orderDetailDTO=new OrderDetailDTO();
        orderDetailDTO.setOrderId(order.getId());

        String status = order.getStatus();
        if (status.equals("new")) {
            orderDetailDTO.setStatus("订单待处理");
        }
        else if (status.equals("dispatching")) {
            orderDetailDTO.setStatus("配送中");
        }
        else if (status.equals("completed")) {
            orderDetailDTO.setStatus("订单已完成");
        }
        else {
            orderDetailDTO.setStatus("无法识别");
        }

        orderDetailDTO.setTelPhone(order.getUser().getTelPhone());
        orderDetailDTO.setAddress(order.getAddress());
        orderDetailDTO.setCreateTime(order.getCreateTime());
        Set<OrderGoods> orderGoodses=order.getOrderGoodses();
        List<GoodsInOrderDTO> goodsInOrderDTOs=new ArrayList<GoodsInOrderDTO>();
        for(OrderGoods orderGoods:orderGoodses){
            GoodsInOrderDTO goodsInOrderDTO=new GoodsInOrderDTO(orderGoods.getId(),orderGoods.getGoodsName(),
                    orderGoods.getGoodsPrice(),orderGoods.getGoodsPictureUrl(),orderGoods.getCount());
            goodsInOrderDTOs.add(goodsInOrderDTO);
        }
        orderDetailDTO.setGoodsInOrderDTOList(goodsInOrderDTOs);
        orderDetailDTO.setTotalPrice(order.getTotalPrice());
        return orderDetailDTO;
    }

    public static List<OrderDetailDTO> makeOrderDetailDTOList(List<Orders> ordersList){
        List<OrderDetailDTO> orderDetailDTOs=new ArrayList<OrderDetailDTO>();
        for(Orders orders:ordersList){
            OrderDetailDTO orderDetailDTO=makeOrderDetailDTO(orders);
            orderDetailDTOs.add(orderDetailDTO);
        }
        return orderDetailDTOs;
    }

    public static List<OrderDetailDTO> makeOrderDetailDTOList(Set<Orders> ordersList){
        List<OrderDetailDTO> orderDetailDTOs = new ArrayList<OrderDetailDTO>();
        for(Orders order : ordersList){
            OrderDetailDTO orderDetailDTO = makeOrderDetailDTO(order);
            orderDetailDTOs.add(orderDetailDTO);
        }
        return orderDetailDTOs;
    }

    public static OrderGoodsDTO makeOrderGoodsDTO(OrderGoods orderGoods) {
        OrderGoodsDTO orderGoodsDTO = new OrderGoodsDTO();
        orderGoodsDTO.setId(orderGoods.getId());
        orderGoodsDTO.setName(orderGoods.getGoodsName());
        orderGoodsDTO.setPrice(orderGoods.getGoodsPrice());
        orderGoodsDTO.setQuantity(orderGoods.getCount());
        orderGoodsDTO.setDescription(orderGoods.getGoodsDescription());
        orderGoodsDTO.setPic(orderGoods.getGoodsPictureUrl());

        return orderGoodsDTO;
    }
}
