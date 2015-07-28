package com.community101.web;

import com.community101.core.*;
import com.community101.web.DTO.*;
import com.community101.core.service.CategoryService;
import com.community101.core.service.GoodsService;
import com.community101.core.service.OrdersService;
import com.community101.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerServiceController {
    boolean isFake = true;
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
    public GoodsSummaryDTO getGoodsSummaryInformationById(long id) {
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
    public SubmissionResultsDTO submitOrder(long[] ids, int[] quantities, String phone, String address) {
        Mapper mapper = new Mapper(userService, goodsService);
        OrderDTO orderDTO = mapper.makeOrderDTO(ids, quantities, phone, address);
        SubmissionResultsDTO submissionResultsDTO = new SubmissionResultsDTO();

        submissionResultsDTO.setErrorMessages(orderDTO.getErrorMessages());

        if (submissionResultsDTO.getErrorMessages().size() == 0) {
            Orders order = mapper.makeOrder(orderDTO);
            ordersService.addOrder(order);
            submissionResultsDTO.setOrderId(order.getId());
        }
        else {
            submissionResultsDTO.setOrderId(0);
        }

        return submissionResultsDTO;
    }

    @RequestMapping("/orders")
    public List<OrderDetailDTO> getOrdersOfCertainUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            userId = 0;
        }

        if (isFake) {
            userId = 1;
        }

        User user = userService.findUserById(userId);
        if (user == null) {
            return new LinkedList<OrderDetailDTO>();
        }
        else {
            return Mapper.makeOrderDetailDTOList(user.getOrderses());
        }
    }

    @RequestMapping("/order/goods")
    public OrderGoodsDTO getOrderGoodsDetails(long goodsId)
    {
        OrderGoods orderGoods = ordersService.findOrderGoodsById(goodsId);
        OrderGoodsDTO orderGoodsDTO = Mapper.makeOrderGoodsDTO(orderGoods);
        return orderGoodsDTO;
    }
}
