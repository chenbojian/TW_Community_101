package com.community101.web;

import com.community101.core.Category;
import com.community101.core.DTO.CategoryDTO;
import com.community101.core.DTO.GoodsSimpleDTO;
import com.community101.core.DTO.GoodsDetailedDTO;
import com.community101.core.Goods;
import com.community101.core.service.CategoryService;
import com.community101.core.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
@RestController
@RequestMapping("/api/customer")
public class GoodsInformationController {
    private boolean is_fake = false;

    private CategoryService categoryService;

    private GoodsService goodsService;

    @Autowired
    public GoodsInformationController(CategoryService categoryService, GoodsService goodsService) {
        this.categoryService = categoryService;
        this.goodsService = goodsService;
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
    public List<GoodsSimpleDTO> listAllGoodsOfCertainCategory(long cid) {
        if (is_fake) {
            List<GoodsSimpleDTO> goodsThinList = new LinkedList<GoodsSimpleDTO>();
            goodsThinList.add(new GoodsSimpleDTO(1, "????1", 1221, "//baidu.com/"));
            goodsThinList.add(new GoodsSimpleDTO(2, "????2", 2221, "//baidu.com/"));
            goodsThinList.add(new GoodsSimpleDTO(3, "????3", 3221, "//baidu.com/"));
            goodsThinList.add(new GoodsSimpleDTO(4, "????4", 4221, "//baidu.com/"));

            return  goodsThinList;
        }
        else {
            Category category = categoryService.findCategoryById(cid);
            Set<Goods> goodses = category.getGoodses();
            List<GoodsSimpleDTO> goodsSimpleDTOList = new LinkedList<GoodsSimpleDTO>();
            for (Goods goods : goodses) {
                GoodsSimpleDTO goodsSimpleDTO = new GoodsSimpleDTO(goods.getId(), goods.getName(), goods.getPrice(), goods.getPictureUrl());
                goodsSimpleDTOList.add(goodsSimpleDTO);
            }
            return goodsSimpleDTOList;
        }
    }

    @RequestMapping("/goods/simple")
    public GoodsSimpleDTO getGoodsSimpleInformationById(long id) {
        if (is_fake) {
            return new GoodsSimpleDTO(id, "Fake Goods from web API", 1212, "//baidu.com/");
        }
        else {
            Goods goods = goodsService.findGoodsById(id);
            GoodsSimpleDTO goodsSimpleDTO = new GoodsSimpleDTO(goods.getId(), goods.getName(), goods.getPrice(), goods.getPictureUrl());
            return goodsSimpleDTO;
        }
    }

    @RequestMapping("goods/details")
    public GoodsDetailedDTO getGoodsGetailsById(long id) {
        if (is_fake) {
            return new GoodsDetailedDTO(id, "fake goods with details", 2143, "//baidu.com/", "very good from web API");
        }
        else {
            Goods goods = goodsService.findGoodsById(id);
            GoodsDetailedDTO goodsDetailedDTO = new GoodsDetailedDTO(goods.getId(), goods.getName(), goods.getPrice(), goods.getPictureUrl(), goods.getDescription());
            return goodsDetailedDTO;
        }
    }

}
