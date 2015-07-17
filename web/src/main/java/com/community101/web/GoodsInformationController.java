package com.community101.web;

import com.community101.core.CategoryDTO;
import com.community101.core.GoodsDTO;
import com.community101.core.GoodsDetailedDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
@RestController
@RequestMapping("/api/customer")
public class GoodsInformationController {
    private boolean is_fake = true;

    @RequestMapping("/categories")
    public List<CategoryDTO> listAllCategories() {
        if (is_fake) {
            List<CategoryDTO> categoryList = new LinkedList<CategoryDTO>();
            categoryList.add(new CategoryDTO(1, "??"));
            categoryList.add(new CategoryDTO(2, "????"));

            return categoryList;
        }
        else {
            throw new NotImplementedException();
        }

    }

    @RequestMapping("/goods")
    public List<GoodsDTO> listAllGoodsOfCertainCategory(long cid) {
        if (is_fake) {
            List<GoodsDTO> goodsThinList = new LinkedList<GoodsDTO>();
            goodsThinList.add(new GoodsDTO(1, "????1", 1221, "//baidu.com/"));
            goodsThinList.add(new GoodsDTO(2, "????2", 2221, "//baidu.com/"));
            goodsThinList.add(new GoodsDTO(3, "????3", 3221, "//baidu.com/"));
            goodsThinList.add(new GoodsDTO(4, "????4", 4221, "//baidu.com/"));

            return  goodsThinList;
        }
        else {
            throw new NotImplementedException();
        }
    }

    @RequestMapping("/good")
    public GoodsDTO getGoodsInformationById(long id) {
        if (is_fake) {
            return new GoodsDTO(id, "Fake Goods", 1212, "//baidu.com/");
        }
        else {
            throw new NotImplementedException();
        }
    }

    @RequestMapping("goods/details")
    public GoodsDetailedDTO getGoodsGetailsById(long id) {
        if (is_fake) {
            return new GoodsDetailedDTO(id, "fake goods", 2143, "//baidu.com/", "very good");
        }
        else {
            throw new NotImplementedException();
        }
    }


}
