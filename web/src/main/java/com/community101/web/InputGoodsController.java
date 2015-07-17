package com.community101.web;

import com.community101.core.DTO.InputGoodsDTO;
import com.community101.core.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by chenbojian on 7/17/15.
 */
@RestController
@RequestMapping("/input-goods")
public class InputGoodsController {
    private GoodsService goodsService;

    @Autowired
    public InputGoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value = {"","/"})
    public ModelAndView inputGoodsPage() {
        ModelAndView modelAndView = new ModelAndView("input-goods");
        modelAndView.addObject("inputGoodsDTO", new InputGoodsDTO());
        return modelAndView;
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    public ModelAndView inputGoods(@ModelAttribute InputGoodsDTO inputGoodsDTO,HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/");
        String contextPath = session.getServletContext().getContextPath();
        try {
            inputGoodsDTO.savePicture(realPath,contextPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        goodsService.save(inputGoodsDTO.toGoods());
        return new ModelAndView();
    }
}
