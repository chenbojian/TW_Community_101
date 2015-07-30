package com.community101.web;

import com.community101.core.User;
import com.community101.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by chenjian on 7/27/15.
 */
@RestController
@RequestMapping("/customer")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(String telPhone, String password, String SMS_code, HttpServletRequest request){
        User user = userService.findUserByTel(telPhone);
        HttpSession session = request.getSession();
        if(user!=null){
            user.setPassword(password);
            userService.updateUser(user);
            session.setAttribute("userId",user.getId());
        }else{
            user = new User();
            user.setTelPhone(telPhone);
            user.setPassword(password);
            userService.addUser(user);
            user = userService.findUserByTel(telPhone);
            session.setAttribute("userId",user.getId());
        }
        return new ModelAndView("redirect:" + request.getContextPath());
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(String telPhone, String password, HttpServletRequest request, HttpServletResponse response) throws Exception{
        User user = userService.findUserByTel(telPhone);
        HttpSession session = request.getSession();
        if(user!=null){
            if(user.getPassword().equals(password)){
                session.setAttribute("userId", user.getId());
                System.out.println("success");
                response.sendRedirect(request.getContextPath() + "/");
            }else{
                System.out.println("failed");
                response.getWriter().write("<script language=javascript>alert('password is wrong!');window.location.href='" + request.getContextPath() + "/customer/login/';</script>");
            }
        }else response.getWriter().write("<script language=javascript>alert('telPhone is not signed up!');window.location.href='" + request.getContextPath() + "/customer/login/';</script>");

    }
}
