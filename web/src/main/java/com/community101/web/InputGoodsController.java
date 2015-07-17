package com.community101.web;

import com.community101.core.DTO.InputGoodsDTO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        ModelAndView modelAndView = new ModelAndView("input-goods");
        modelAndView.addObject("inputGoodsDTO", new InputGoodsDTO());
        return modelAndView;
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    public ModelAndView inputGoods(@ModelAttribute InputGoodsDTO inputGoodsDTO){
        return new ModelAndView();
    }
}
