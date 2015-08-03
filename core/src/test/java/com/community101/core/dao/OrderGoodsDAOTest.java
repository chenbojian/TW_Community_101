package com.community101.core.dao;

import com.community101.core.OrderGoods;
import com.community101.core.Orders;
import com.community101.core.User;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by jiaoming on 7/17/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/springTest.xml")
@TransactionConfiguration
@Transactional
public class OrderGoodsDAOTest {
    @Autowired
    private OrderGoodsDAO orderGoodsDAO;

    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private javax.sql.DataSource dataSource;

    @Transactional
    @Rollback
    @Test
    public void should_return_correct_number_of_orderGoods(){
        List<OrderGoods> orderGoodsList=orderGoodsDAO.orderGoodsList();
        assertTrue(orderGoodsList.size() > 0);
        assertEquals("bread",orderGoodsList.get(0).getGoodsName());
    }

    @Transactional
    @Rollback
    @Test
    public void should_save_order_goods() {
        Orders order = new Orders();
        User user = new User();
        String telPhone = "123456";
        user.setTelPhone(telPhone);
        userDAO.addUser(user);

        order.setUser(user);
        String address = "Beijing";
        order.setAddress(address);

        ordersDAO.addOrder(order);
        long orderId = order.getId();

        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setGoodsName("test");
        orderGoods.setOrders(order);
        orderGoodsDAO.addOrderGoods(orderGoods);
        long orderGoodsId = orderGoods.getId();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int count = jdbcTemplate.queryForObject("select count(*) from ORDER_GOODS where ORDER_ID = ? and ID = ?", new Object[] {orderId, orderGoodsId}, Integer.class);
        assertEquals(1, count);
    }
}
