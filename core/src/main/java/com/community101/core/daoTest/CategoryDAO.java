package com.community101.core.daoTest;

import com.community101.core.Category;
import com.community101.core.Goods;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiaoming on 7/17/15.
 */
@Repository
@Transactional
public class CategoryDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public CategoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Category> listCategory() {
        return sessionFactory.getCurrentSession().createQuery("from Category").list();
    }

    public Category findCategoryById(long categoryId) {
        return (Category) sessionFactory.getCurrentSession()
                .get(Category.class, categoryId);
    }

    public void deleteCategoryById(long id) {
        Category category = findCategoryById(id);
        if (category != null) {
            for (Goods goods : category.getGoodses()) {
                goods.setCategory(null);
                sessionFactory.getCurrentSession()
                        .update(goods);
            }
            sessionFactory.getCurrentSession()
                    .delete(category);
        }
    }

    public void save(Category category) {
        sessionFactory.getCurrentSession()
                .save(category);
    }

    public void update(Category category) {
        sessionFactory.getCurrentSession()
                .update(category);
    }
}
