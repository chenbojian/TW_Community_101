package com.community101.admin.api;

import com.community101.core.Category;
import com.community101.core.service.CategoryService;
import com.community101.core.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chenbojian on 7/28/15.
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @JsonView(Views.CategoryBase.class)
    public List<Category> listCategories(){
        return categoryService.listCategory();
    }


}
