package com.community101.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by MiffyLiye on 29/07/2015.
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/order-history/", method = RequestMethod.GET)
    public ModelAndView getOrderHistory() {
        return new ModelAndView("orderHistory");
    }

    @RequestMapping(value = "/shopping-cart/", method = RequestMethod.GET)
    public ModelAndView getShoppingCart() {
        return new ModelAndView("shoppingCart");
    }

    @RequestMapping(value = "/order-details/", method = RequestMethod.GET)
    public ModelAndView getOrderDetails() {
        return new ModelAndView("orderDetails");
    }

    @RequestMapping(value = "/signup/", method = RequestMethod.GET)
    public ModelAndView getSignUp() {
        return new ModelAndView("signup");
    }

    @RequestMapping(value = "/login/", method = RequestMethod.GET)
    public ModelAndView getLogIn() {
        return new ModelAndView("login");
    }

}
