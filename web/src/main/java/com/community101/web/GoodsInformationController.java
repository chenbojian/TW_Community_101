package com.community101.web;

import com.community101.core.Category;
import com.community101.core.DTO.CategoryDTO;
import com.community101.core.DTO.GoodsDTO;
import com.community101.core.DTO.GoodsDetailedDTO;
import com.community101.core.Goods;
import com.community101.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
@RestController
@RequestMapping("/api/customer")
public class GoodsInformationController {
    private boolean is_fake = true;

    private CategoryService categoryService;

    @Autowired
    public GoodsInformationController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/categories")
    public List<CategoryDTO> listAllCategories() {
        if (is_fake) {
            List<CategoryDTO> categoryList = new LinkedList<CategoryDTO>();
            categoryList.add(new CategoryDTO(1, "??"));
            categoryList.add(new CategoryDTO(2, "????"));

            return categoryList;
        }
        else {
            List<Category> categoryList = categoryService.listCategory();
            List<CategoryDTO> categoryDTOList = new LinkedList<CategoryDTO>();
            for (Category category : categoryList) {
                CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName());
                categoryDTOList.add(categoryDTO);
            }
            return categoryDTOList;
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
            Category category = categoryService.findCategoryById(cid);
            Set<Goods> goodses = category.getGoodses();
            List<GoodsDTO> goodsDTOList = new LinkedList<GoodsDTO>();
            for (Goods goods : goodses) {
                GoodsDTO goodsDTO = new GoodsDTO(goods.getId(), goods.getName(), goods.getPrice(), goods.getPictureUrl());
                goodsDTOList.add(goodsDTO);
            }
            return goodsDTOList;
        }
    }

    @RequestMapping("/goods/simple")
    public GoodsDTO getGoodsSimpleInformationById(long id) {
        if (is_fake) {
            return new GoodsDTO(id, "Fake Goods from web API", 1212, "//baidu.com/");
        }
        else {
            throw new NotImplementedException();
        }
    }

    @RequestMapping("goods/details")
    public GoodsDetailedDTO getGoodsGetailsById(long id) {
        if (is_fake) {
            return new GoodsDetailedDTO(id, "fake goods with details", 2143, "//baidu.com/", "very good from web API");
        }
        else {
            throw new NotImplementedException();
        }
    }

}
