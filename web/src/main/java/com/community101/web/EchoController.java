package com.community101.web;

import com.community101.core.DTO.GoodsInSubmissionDTO;
import com.community101.core.DTO.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

/**
 * Created by MiffyLiye on 21/07/2015.
 */
@RestController
@RequestMapping("/api/echo")
public class EchoController {
    @RequestMapping(value = "/order/submit", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO echoSubmittedOrder(long[] id, int[] quantity, String phone, String address) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAddress(address);
        orderDTO.setPhone(phone);
        orderDTO.setGoodsList(new LinkedList<GoodsInSubmissionDTO>());
        for (int i = 0; i < id.length; i++) {
            GoodsInSubmissionDTO goodsInSubmissionDTO = new GoodsInSubmissionDTO(id[i], quantity[i]);
            orderDTO.getGoodsList().add(goodsInSubmissionDTO);
        }
        return orderDTO;
    }
}
