package com.community101.core.service;

import com.community101.core.Category;
import com.community101.core.dao.CategoryDAO;
import com.community101.core.dao.GoodsDAO;
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

    public void deleteCategory(long id) {
        categoryDAO.deleteCategoryById(id);

    }

    public void save(Category category) {
        categoryDAO.save(category);
    }

    public void update(Category category) {
        categoryDAO.update(category);
    }
}
