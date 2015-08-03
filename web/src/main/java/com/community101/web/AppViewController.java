package com.community101.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/app")
public class AppViewController {
    @RequestMapping(value = {"/", "/default/"}, method = RequestMethod.GET)
    public ModelAndView getIndex() {
        return new ModelAndView("webApp");
    }

    @RequestMapping(value = {"/demonstration/"}, method = RequestMethod.GET)
    public ModelAndView getDemonstration() {
        return new ModelAndView("demonstrationView");
    }

    @RequestMapping(value = "/order-history/", method = RequestMethod.GET)
    public ModelAndView getOrderHistory() {
        return new ModelAndView("orderHistoryView");
    }

    @RequestMapping(value = "/shopping-cart/", method = RequestMethod.GET)
    public ModelAndView getShoppingCart() {
        return new ModelAndView("shoppingCartView");
    }

    @RequestMapping(value = "/order-details/", method = RequestMethod.GET)
    public ModelAndView getOrderDetails() {
        return new ModelAndView("orderDetailsView");
    }

    @RequestMapping(value = "/signup/", method = RequestMethod.GET)
    public ModelAndView getSignUp() {
        return new ModelAndView("signupView");
    }

    @RequestMapping(value = "/login/", method = RequestMethod.GET)
    public ModelAndView getLogIn() {
        return new ModelAndView("loginView");
    }
}
