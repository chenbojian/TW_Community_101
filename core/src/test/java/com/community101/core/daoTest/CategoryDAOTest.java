package com.community101.core.daoTest;

import com.community101.core.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * Created by jiaoming on 7/17/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springTest.xml")
@TransactionConfiguration
@Transactional
public class CategoryDAOTest {

    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    @Rollback
    @Test
    public void should_return_correct_number_of_category(){
        List<Category> categoryList=categoryDAO.listCategory();
        assertEquals(4,categoryList.size());
        assertEquals("drink",categoryList.get(1).getName());
    }
}
