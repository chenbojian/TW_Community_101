package com.community101.web;

import com.community101.core.Category;
import com.community101.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chenbojian on 7/28/15.
 */
@RestController
@RequestMapping("/manage/category")
public class CategoryManageController {
    private CategoryService categoryService;

    @Autowired
    public CategoryManageController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView categoryManagePage(){
        ModelAndView modelAndView = new ModelAndView("manageCategory");
        modelAndView.addObject("categories", categoryService.listCategory());
        return modelAndView;
    }
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteCategoryById(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return new ModelAndView("redirect:/manage/category");
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        categoryService.save(category);
        return new ModelAndView("redirect:/manage/category");
    }
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editCategoryById(@PathVariable long id) {
        return new ModelAndView("redirect:/manage/category");
    }

}
