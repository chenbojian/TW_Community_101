package com.community101.core.dao;

import com.community101.core.Goods;
import com.community101.core.OrderGoods;
import com.community101.core.Orders;
import com.community101.core.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

    @Autowired
    private javax.sql.DataSource dataSource;

    @Transactional
    @Rollback
    @Test
    public void should_return_correct_number_of_orders(){
        List<Orders> ordersList=ordersDAO.listOrders();
        assertTrue(ordersList.size() > 0);
    }

    @Transactional
    @Rollback
    @Test
    public void should_return_new_status_orders(){
        List<Orders> newOrdersList=ordersDAO.listNewOrders();
        assertTrue(newOrdersList.size() > 0);
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
        userDAO.addUser(user);

        order.setUser(user);
        String address = "Beijing";
        order.setAddress(address);

        ordersDAO.addOrder(order);
        long id = order.getId();
        order = ordersDAO.findOrdersById(id);

        assertEquals(telPhone, order.getUser().getTelPhone());
        assertEquals(address, order.getAddress());
    }

    @Transactional
    @Rollback
    @Test
    public void should_save_total_price_in_new_order() {
        Orders order = new Orders();
        User user = new User();
        String telPhone = "123456";
        user.setTelPhone(telPhone);
        userDAO.addUser(user);

        order.setUser(user);
        String address = "Beijing";
        order.setAddress(address);

        order.setTotalPrice(123456);
        ordersDAO.addOrder(order);
        long id = order.getId();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int price = jdbcTemplate.queryForObject("select TOTAL_PRICE from ORDERS where ID = ?", new Object[] { id }, Integer.class);
        assertEquals(123456, price);
    }
}