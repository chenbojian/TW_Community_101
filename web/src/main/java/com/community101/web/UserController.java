package com.community101.web;

import com.community101.core.User;
import com.community101.core.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by chenjian on 7/27/15.
 */
@RestController
@RequestMapping("/api/customer")
public class UserController {
    Gson gson = new Gson();
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public void signIn(@RequestBody String data, HttpSession session){
        UserWithSMSCode o = gson.fromJson(data, UserWithSMSCode.class);
        User user = userService.findUserByTel(o.getTelPhone());
        if(user!=null){
            user.setPassword(o.getPassword());
            userService.updateUser(user);
            session.setAttribute("userId",user.getId());
        }else{
            user = new User();
            user.setTelPhone(o.getTelPhone());
            user.setPassword(o.getPassword());
            userService.addUser(user);
            user = userService.findUserByTel(o.getTelPhone());
            session.setAttribute("userId",user.getId());
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody String data, HttpSession session){
        System.out.println(data);

        User u = gson.fromJson(data, User.class);
        User user = userService.findUserByTel(u.getTelPhone());
        if(user.getPassword().equals(u.getPassword())){
            session.setAttribute("userId", user.getId());
            System.out.println("success");
            return "success";
        }else{
            System.out.println("failed");
            return "failed";
        }
    }

    class UserWithSMSCode{
        private String telPhone;
        private String password;
        private String SMS_code;

        public String getSMS_code() {
            return SMS_code;
        }

        public void setSMS_code(String SMS_code) {
            this.SMS_code = SMS_code;
        }

        public String getPassword() {

            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTelPhone() {

            return telPhone;
        }

        public void setTelPhone(String telPhone) {
            this.telPhone = telPhone;
        }
    }
}
