package com.community101.core.daoTest;

import com.community101.core.Orders;
import com.community101.core.User;
import com.community101.core.dao.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by jiaoming on 7/17/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:springTest.xml")
@TransactionConfiguration
@Transactional
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Rollback
    @Test
    public void should_return_correct_number_of_users(){
        List<User>  userList=userDAO.listUsers();
        assertTrue(userList.size() > 0);
    }

    @Transactional
    @Rollback
    @Test
    public void should_find_user_by_tel() {
        String tel = "18888888880";
        User user = userDAO.findUserByTel(tel);
        assertNotNull(user);
    }

    @Transactional
    @Rollback
    @Test
    public void should_add_user() {
        User user = new User();
        String tel = "12345678";
        user.setTelPhone(tel);
        user.setOrderses(new LinkedHashSet<Orders>());
        userDAO.addUser(user);
        long id = user.getId();
        user = userDAO.findUserByTel(tel);
        assertEquals(id, user.getId());
    }

}
