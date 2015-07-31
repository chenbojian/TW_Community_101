package com.community101.admin.api;

import com.community101.core.Category;
import com.community101.core.service.CategoryService;
import com.community101.core.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(Views.CategoryBase.class)
    public List<Category> listAll() {
        return categoryService.listCategory();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void getCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteCategory(@PathVariable long id,@RequestBody Category newCategory) {
        Category category = categoryService.findCategoryById(id);
        category.setName(newCategory.getName());
        categoryService.update(category);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addCategory(@RequestBody Category category){
        categoryService.save(category);
        return category.getId();
    }

}
