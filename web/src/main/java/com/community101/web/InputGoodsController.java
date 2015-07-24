package com.community101.web;

import com.community101.core.Category;
import com.community101.core.DTO.InputGoodsDTO;
import com.community101.core.service.CategoryService;
import com.community101.core.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenbojian on 7/17/15.
 */
@RestController
@RequestMapping("/input-goods")
public class InputGoodsController {
    private GoodsService goodsService;
    private CategoryService categoryService;

    @Autowired
    public InputGoodsController(GoodsService goodsService, CategoryService categoryService) {
        this.goodsService = goodsService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = {"", "/"})
    public ModelAndView inputGoodsPage() {
        ModelAndView modelAndView = new ModelAndView("inputGoods");
        modelAndView.addObject("inputGoodsDTO", new InputGoodsDTO());
        List<Category> categories = categoryService.listCategory();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView inputGoods(@ModelAttribute InputGoodsDTO inputGoodsDTO, HttpSession session,
                                   Model model) {
        String realPath = session.getServletContext().getRealPath("/");
        String contextPath = session.getServletContext().getContextPath();
        try {
            inputGoodsDTO.savePicture(realPath, contextPath);
            inputGoodsDTO.setCategory(categoryService.findCategoryById(inputGoodsDTO.getCategoryId()));
            goodsService.save(inputGoodsDTO.toGoods());
            String message = "Add goods successfully!";
            model.addAttribute("message", message);
        } catch (IOException e) {

            e.printStackTrace();
        }
        //return inputGoodsPage();
        return new ModelAndView("redirect:/back-navi.html");
    }
}
