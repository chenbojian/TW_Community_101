package com.community101.core.service;

import com.community101.core.User;
import com.community101.core.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MiffyLiye on 20/07/2015.
 */
@Service
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public User findUserByTel(String tel) {
        return userDAO.findUserByTel(tel);
    }

    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    public void updateUser(User user){
        userDAO.updateUser(user);
    }
}
