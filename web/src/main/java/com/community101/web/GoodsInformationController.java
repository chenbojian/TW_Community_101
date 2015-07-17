package com.community101.web;

import com.community101.core.CategoryDTO;
import com.community101.core.GoodsDTO;
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
    public List<CategoryDTO> listAllCategories() {
        if (is_fake) {
            List<CategoryDTO> categoryList = new LinkedList<CategoryDTO>();
            categoryList.add(new CategoryDTO(1, "shi"));
            categoryList.add(new CategoryDTO(2, "????"));

            return categoryList;
        }
        else {
            throw new NotImplementedException();
        }

    }

    @RequestMapping("/good/all")
    public List<GoodsDTO> listAllGoodsOfCertainCategory(long cid) {
        if (is_fake) {
            List<GoodsDTO> goodsThinList = new LinkedList<GoodsDTO>();
            goodsThinList.add(new GoodsDTO(1, "????1", "12.21", "//baidu.com/"));
            goodsThinList.add(new GoodsDTO(2, "????2", "22.21", "//baidu.com/"));
            goodsThinList.add(new GoodsDTO(3, "????3", "32.21", "//baidu.com/"));
            goodsThinList.add(new GoodsDTO(4, "????4", "42.21", "//baidu.com/"));

            return  goodsThinList;
        }
        else {
            throw new NotImplementedException();
        }
    }

}