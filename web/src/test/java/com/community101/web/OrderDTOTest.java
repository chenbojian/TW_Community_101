package com.community101.web;

import com.community101.core.service.GoodsService;
import com.community101.web.DTO.GoodsInSubmissionDTO;
import com.community101.web.DTO.OrderDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

import static org.junit.Assert.*;


/**
 * Created by jiaoming on 7/17/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
@TransactionConfiguration
@Transactional
public class OrderDTOTest {
    @Autowired
    private GoodsService goodsService;

    private OrderDTO orderDTO = null;

    @Before
    public void SetUp() {
        orderDTO = new OrderDTO(goodsService);
    }

    @Test
    public void order_dto_should_have_3_checkers() {
        assertEquals(3, orderDTO.getCheckers().size());
    }

    @Test
    public void empty_order_dto_should_have_3_errors() {
        assertEquals(3, orderDTO.getErrors().size());
    }

    @Test
    public void valid_order_dto_should_have_no_error() {
        orderDTO.setGoodsList(new LinkedList<GoodsInSubmissionDTO>());
        orderDTO.getGoodsList().add(new GoodsInSubmissionDTO(1, 1));
        orderDTO.setPhone("11111111111");
        orderDTO.setAddress("Beijing");

        assertEquals(0, orderDTO.getErrors().size());
    }
}
