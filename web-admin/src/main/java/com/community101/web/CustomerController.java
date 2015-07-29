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
    @RequestMapping(value = "/order-history/", method = RequestMethod.GET)
    public ModelAndView getOrderHistory() {
        ModelAndView modelAndView = new ModelAndView("customerOrderHistory");
        return modelAndView;
    }
}
