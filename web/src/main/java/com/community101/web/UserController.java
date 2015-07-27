package com.community101.web;

import com.community101.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by chenjian on 7/27/15.
 */
@RestController
@RequestMapping("/api/customer")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public void signIn(HttpRequest request, HttpSession session){

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpRequest request, HttpSession session){

    }
}
