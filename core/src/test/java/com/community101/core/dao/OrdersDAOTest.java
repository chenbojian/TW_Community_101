package com.community101.core.dao;

import com.community101.core.Orders;
import com.community101.core.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Rollback
    @Test
    public void should_return_correct_number_of_orders(){
        List<Orders> ordersList=ordersDAO.listOrders();
        assertEquals(2,ordersList.size());
    }

    @Transactional
    @Rollback
    @Test
    public void should_return_new_status_orders(){
        List<Orders> newOrdersList=ordersDAO.listNewOrders();
        assertEquals(1,newOrdersList.size());
        int totalPrice=newOrdersList.get(0).getTotalPrice();
        assertEquals(300,totalPrice);
    }

    @Transactional
    @Rollback
    @Test
    public void should_add_new_order() {
        Orders order = new Orders();
        User user = new User();
        user.setTelPhone("123456");
        order.setUser(user);
        ordersDAO.addOrder(order);
        long id = order.getId();
        order = ordersDAO.findOrdersById(id);
        assertNotNull(order);
    }

    @Transactional
    @Rollback
    @Test
    public void should_add_user_and_address_in_new_order() {
        Orders order = new Orders();
        User user = new User();
        String telPhone = "123456";
        user.setTelPhone(telPhone);
        order.setUser(user);
        String address = "Beijing";
        order.setAddress(address);

        ordersDAO.addOrder(order);
        long id = order.getId();
        order = ordersDAO.findOrdersById(id);

        assertEquals(telPhone, order.getUser().getTelPhone());
        assertEquals(address, order.getAddress());
    }
}