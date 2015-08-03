package com.community101.web;

import com.community101.core.Category;
import com.community101.core.Goods;
import com.community101.core.service.CategoryService;
import com.community101.core.service.GoodsService;
import com.community101.web.DTO.CategoryDTO;
import com.community101.web.DTO.GoodsDetailedDTO;
import com.community101.web.DTO.GoodsSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MiffyLiye on 31/07/2015.
 */

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    private CategoryService categoryService;
    private GoodsService goodsService;

    @Autowired
    public GoodsController(
            CategoryService categoryService,
            GoodsService goodsService) {
        this.categoryService = categoryService;
        this.goodsService = goodsService;
    }

    @RequestMapping("/categories")
    public List<CategoryDTO> listAllCategories() {
        List<CategoryDTO> categoryDTOList = Mapper.makeCategoryDTOList(categoryService.listCategory());

        return categoryDTOList;
    }

    @RequestMapping("/")
    public List<GoodsSummaryDTO> listAllGoodsOfCertainCategory(long cid) {
        if (cid == 0) {
            return listAllGoods();
        }
        if (cid == -1){
            return listGoodsWithoutCategory();
        }

        List<GoodsSummaryDTO> goodsSummaryDTOList = null;
        Category category = categoryService.findCategoryById(cid);
        if (category != null) {
            goodsSummaryDTOList = Mapper.makeGoodsSummaryDTOList(category.getGoodses());
        }
        else {
            goodsSummaryDTOList = new LinkedList<GoodsSummaryDTO>();
        }

        return goodsSummaryDTOList;
    }

    @RequestMapping("/goodsWithoutCategory")
    public List<GoodsSummaryDTO> listGoodsWithoutCategory(){
        return Mapper.makeGoodsSummaryDTOList(goodsService.listGoodsWithoutCategory());
    }

    @RequestMapping("/summary/all")
    public List<GoodsSummaryDTO> listAllGoods() {
        return Mapper.makeGoodsSummaryDTOList(goodsService.listGoods());
    }

    @RequestMapping("/summary")
    public GoodsSummaryDTO getGoodsSummaryInformationById(long id) {
        GoodsSummaryDTO goodsSummaryDTO = null;
        Goods goods = goodsService.findGoodsById(id);
        if (goods != null) {
            goodsSummaryDTO = Mapper.makeGoodsSummayDTO(goods);
        }
        else {
            goodsSummaryDTO =new GoodsSummaryDTO(0,
                    "Not Found",
                    0,
                    "");
        }
        return goodsSummaryDTO;
    }

    @RequestMapping("/details")
    public GoodsDetailedDTO getGoodsGetailsById(long id) {
        GoodsDetailedDTO goodsDetailedDTO = null;
        Goods goods = goodsService.findGoodsById(id);
        if (goods != null) {
            goodsDetailedDTO = Mapper.makeGoodsDetailedDTO(goods);
        }
        else {
            goodsDetailedDTO = new GoodsDetailedDTO(0,
                    "Not Found",
                    0,
                    "",
                    "");
        }
        return goodsDetailedDTO;
    }
}
