package com.community101.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chenjian on 7/30/15.
 */
@RestController
public class AdministratorController {
    @RequestMapping(value = "/navbar", method = RequestMethod.GET)
    public ModelAndView navbarTemplate() {
        return new ModelAndView("template/navbar");
    }

    @RequestMapping("/order-manager")
    public ModelAndView getOrderManager() {
        return new ModelAndView("orderManager");
    }

    @RequestMapping("/order-status")
    public ModelAndView getOrderStatus() {
        return new ModelAndView("orderStatus");
    }
}
