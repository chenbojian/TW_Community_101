package com.community101.web;

import com.community101.core.OrderGoods;
import com.community101.core.Orders;
import com.community101.core.User;
import com.community101.core.service.GoodsService;
import com.community101.core.service.OrdersService;
import com.community101.core.service.UserService;
import com.community101.web.DTO.OrderDTO;
import com.community101.web.DTO.OrderDetailDTO;
import com.community101.web.DTO.OrderGoodsDTO;
import com.community101.web.DTO.SubmissionResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenjian on 7/17/15.
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private GoodsService goodsService;
    private UserService userService;
    private OrdersService ordersService;

    @Autowired
    public OrderController(
            GoodsService goodsService,
            UserService userService,
            OrdersService ordersService) {
        this.goodsService = goodsService;
        this.userService = userService;
        this.ordersService = ordersService;
    }

    @RequestMapping(value = "/detail")
    public OrderDetailDTO getOrderDetail(long orderId){
        Orders orders=ordersService.findOrdersById(orderId);
        return Mapper.makeOrderDetailDTO(orders);
    }

    @RequestMapping("/history")
    public List<OrderDetailDTO> getOrdersOfCertainUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            userId = 0L;
        }

        User user = userService.findUserById(userId);
        if (user == null) {
            return new LinkedList<OrderDetailDTO>();
        }
        else {
            return Mapper.makeOrderDetailDTOList(user.getOrderses());
        }
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

    @RequestMapping("/goods")
    public OrderGoodsDTO getOrderGoodsDetails(long goodsId)
    {
        OrderGoods orderGoods = ordersService.findOrderGoodsById(goodsId);
        OrderGoodsDTO orderGoodsDTO = Mapper.makeOrderGoodsDTO(orderGoods);
        return orderGoodsDTO;
    }
}
