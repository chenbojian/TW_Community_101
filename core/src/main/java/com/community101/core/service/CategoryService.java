package com.community101.core.service;

import com.community101.core.Category;
import com.community101.core.daoTest.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenbojian on 7/17/15.
 */
@Service
public class CategoryService {

    private CategoryDAO categoryDAO;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> listCategory() {
        return categoryDAO.listCategory();
    }

    public Category findCategoryById(long categoryId) {
        return categoryDAO.findCategoryById(categoryId);

    }
}
