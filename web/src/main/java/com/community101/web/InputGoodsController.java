package com.community101.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chenbojian on 7/17/15.
 */
@RestController
@RequestMapping("/input-goods")
public class InputGoodsController {
    @RequestMapping(value = {"","/"})
    public ModelAndView inputGoodsPage() {
        return new ModelAndView("input-goods");
    }
}
