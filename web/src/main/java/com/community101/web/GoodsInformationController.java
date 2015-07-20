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
    private CategoryService categoryService;

    private GoodsService goodsService;

    @Autowired
    public GoodsInformationController(CategoryService categoryService, GoodsService goodsService) {
        this.categoryService = categoryService;
        this.goodsService = goodsService;
    }

    @RequestMapping("/categories")
    public List<CategoryDTO> listAllCategories() {
        List<Category> categoryList = categoryService.listCategory();
        List<CategoryDTO> categoryDTOList = new LinkedList<CategoryDTO>();
        for (Category category : categoryList) {
            CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName());
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    @RequestMapping("/goods")
    public List<GoodsSimpleDTO> listAllGoodsOfCertainCategory(long cid) {
        Category category = categoryService.findCategoryById(cid);
        Set<Goods> goodses = category.getGoodses();
        List<GoodsSimpleDTO> goodsSimpleDTOList = new LinkedList<GoodsSimpleDTO>();
        for (Goods goods : goodses) {
            GoodsSimpleDTO goodsSimpleDTO = new GoodsSimpleDTO(goods.getId(), goods.getName(), goods.getPrice(), goods.getPictureUrl());
            goodsSimpleDTOList.add(goodsSimpleDTO);
        }
        return goodsSimpleDTOList;
    }

    @RequestMapping("/goods/all")
    public List<GoodsSimpleDTO> listAllGoods() {
        List<GoodsSimpleDTO> goodsSimpleDTOList = new LinkedList<GoodsSimpleDTO>();
        for (Category category : categoryService.listCategory()) {
            for (GoodsSimpleDTO goodsSimpleDTO : listAllGoodsOfCertainCategory(category.getId())) {
                goodsSimpleDTOList.add(goodsSimpleDTO);
            }
        }
        return goodsSimpleDTOList;
    }


    @RequestMapping("/goods/simple")
    public GoodsSimpleDTO getGoodsSimpleInformationById(long id) {
        Goods goods = goodsService.findGoodsById(id);
        GoodsSimpleDTO goodsSimpleDTO = new GoodsSimpleDTO(goods.getId(), goods.getName(), goods.getPrice(), goods.getPictureUrl());
        return goodsSimpleDTO;
    }

    @RequestMapping("goods/details")
    public GoodsDetailedDTO getGoodsGetailsById(long id) {
        Goods goods = goodsService.findGoodsById(id);
        GoodsDetailedDTO goodsDetailedDTO = new GoodsDetailedDTO(goods.getId(), goods.getName(), goods.getPrice(), goods.getPictureUrl(), goods.getDescription());
        return goodsDetailedDTO;
    }
}
