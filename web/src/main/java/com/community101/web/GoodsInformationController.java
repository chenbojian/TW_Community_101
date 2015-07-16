package com.community101.web;

import com.community101.core.Category;
import com.community101.core.GoodsThin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
@RestController
@RequestMapping("/api")
public class GoodsInformationController {
    private boolean is_fake = true;

    @RequestMapping("/category")
    public List<Category> listAllCategories() {
        if (is_fake) {
            List<Category> categoryList = new LinkedList<Category>();
            categoryList.add(new Category(1, "??"));
            categoryList.add(new Category(2, "????"));

            return categoryList;
        }
        else {
            throw new NotImplementedException();
        }

    }

    @RequestMapping("/good/all")
    public List<GoodsThin> listAllGoodsOfCertainCategory(long cid) {
        if (is_fake) {
            List<GoodsThin> goodsThinList = new LinkedList<GoodsThin>();
            goodsThinList.add(new GoodsThin(1, "????1", "12.21", "//baidu.com/"));
            goodsThinList.add(new GoodsThin(2, "????2", "22.21", "//baidu.com/"));
            goodsThinList.add(new GoodsThin(3, "????3", "32.21", "//baidu.com/"));
            goodsThinList.add(new GoodsThin(4, "????4", "42.21", "//baidu.com/"));

            return  goodsThinList;
        }
        else {
            throw new NotImplementedException();
        }
    }
}
