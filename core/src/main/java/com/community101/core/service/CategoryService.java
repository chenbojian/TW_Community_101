package com.community101.core.service;

import com.community101.core.Category;
import com.community101.core.Goods;
import com.community101.core.dao.CategoryDAO;
import com.community101.core.dao.GoodsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created by chenbojian on 7/17/15.
 */
@Service
public class CategoryService {

    private CategoryDAO categoryDAO;
    private GoodsDAO goodsDAO;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO, GoodsDAO goodsDAO) {
        this.categoryDAO = categoryDAO;
        this.goodsDAO = goodsDAO;
    }

    public List<Category> listCategory() {
        return categoryDAO.listCategory();
    }

    public Category findCategoryById(long categoryId) {
        return categoryDAO.findCategoryById(categoryId);

    }

    public void deleteCategory(long id) {
        Category category = categoryDAO.findCategoryById(id);
        if (category != null) {
            for (Goods goods : category.getGoodses()) {
                goods.setCategory(null);
                goodsDAO.update(goods);
            }
            categoryDAO.deleteCategory(category);
        }
    }
}
