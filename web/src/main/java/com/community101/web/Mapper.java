package com.community101.web;

import com.community101.core.*;
import com.community101.web.DTO.*;
import com.community101.core.service.GoodsService;
import com.community101.core.service.UserService;

import java.sql.Timestamp;
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

    public OrderDTO makeOrderDTO(long[] ids, int[] quantities, String phone, String address) {
        OrderDTO orderDTO = new OrderDTO(goodsService);
        orderDTO.setAddress(address);
        orderDTO.setPhone(phone);
        orderDTO.setGoodsList(new LinkedList<GoodsInSubmissionDTO>());
        int len = ids.length <= quantities.length ? ids.length : quantities.length;
        for (int i = 0; i < len; i++) {
            GoodsInSubmissionDTO goodsInSubmissionDTO = new GoodsInSubmissionDTO(ids[i], quantities[i]);
            orderDTO.getGoodsList().add(goodsInSubmissionDTO);
        }
        return orderDTO;
    }

    public Orders makeOrder(OrderDTO orderDTO) {
        Orders order = new Orders();

        order.setAddress(orderDTO.getAddress());
        User user = userService.findUserByTel(orderDTO.getPhone());
        if (user == null) {
            user = new User();
            user.setTelPhone(orderDTO.getPhone());
            //userService.addUser(user);
        }
        order.setUser(user);

        List<GoodsInSubmissionDTO> goodsInSubmissionDTOList = orderDTO.getGoodsList();
        if (goodsInSubmissionDTOList == null) {
            goodsInSubmissionDTOList = new LinkedList<GoodsInSubmissionDTO>();
        }
        Set<OrderGoods> orderGoodsSet = new LinkedHashSet<OrderGoods>();
        for (GoodsInSubmissionDTO goodsInSubmissionDTO : goodsInSubmissionDTOList) {
            Goods goods = goodsService.findGoodsById(goodsInSubmissionDTO.getId());
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setId(goods.getId());
            orderGoods.setCount(goodsInSubmissionDTO.getQuantity());
            orderGoods.setGoodsCategoryName(goods.getCategory().getName());
            orderGoods.setGoodsDescription(goods.getDescription());
            orderGoods.setGoodsName(goods.getName());
            orderGoods.setGoodsPrice(goods.getPrice());
            orderGoods.setGoodsPictureUrl(goods.getPictureUrl());
            orderGoods.setOrders(order);
            orderGoodsSet.add(orderGoods);
        }
        order.setOrderGoodses(orderGoodsSet);
        order.setTotalPrice(order.getBillTotal());
        order.setStatus("new");
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return order;
    }

    public Orders makeOrder(long[] ids, int[] quantities, String phone, String address) {
        OrderDTO orderDTO = makeOrderDTO(ids, quantities, phone, address);
        Orders order = makeOrder(orderDTO);
        return order;
    }

    public static OrderDetailDTO makeOrderDetailDTO(Orders orders){
        OrderDetailDTO orderDetailDTO=new OrderDetailDTO();
        orderDetailDTO.setOrderId(orders.getId());
        orderDetailDTO.setStatus(orders.getStatus());
        orderDetailDTO.setTelPhone(orders.getUser().getTelPhone());
        orderDetailDTO.setAddress(orders.getAddress());
        orderDetailDTO.setCreateTime(orders.getCreateTime());
        Set<OrderGoods> orderGoodses=orders.getOrderGoodses();
        List<GoodsInOrderDTO> goodsInOrderDTOs=new ArrayList<GoodsInOrderDTO>();
        for(OrderGoods orderGoods:orderGoodses){
            GoodsInOrderDTO goodsInOrderDTO=new GoodsInOrderDTO(orderGoods.getId(),orderGoods.getGoodsName(),
                    orderGoods.getGoodsPrice(),orderGoods.getGoodsPictureUrl(),orderGoods.getCount());
            goodsInOrderDTOs.add(goodsInOrderDTO);
        }
        orderDetailDTO.setGoodsInOrderDTOList(goodsInOrderDTOs);
        orderDetailDTO.setTotalPrice(orders.getTotalPrice());
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
