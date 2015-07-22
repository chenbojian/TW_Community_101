package com.community101.web;

import com.community101.core.DTO.GoodsInSubmissionDTO;
import com.community101.core.DTO.OrderDTO;
import com.community101.core.Orders;
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
    public OrderDTO echoSubmittedOrder(long[] ids, int[] quantities, String phone, String address) {
        OrderDTO orderDTO = Mapper.makeOrderDTO(ids, quantities, phone, address);
        return orderDTO;
    }
}
