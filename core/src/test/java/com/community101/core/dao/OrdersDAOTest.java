package com.community101.core.dao;

import com.community101.core.Orders;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jiaoming on 7/17/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@TransactionConfiguration
@Transactional
public class OrdersDAOTest  {

    @Autowired
    private OrdersDAO ordersDAO;

   @Transactional
    @Rollback
    @Test
    public void should_return_correct_number_of_orders(){
        List<Orders> ordersList=ordersDAO.listOrders();
        assertEquals(2,ordersList.size());
    }
}