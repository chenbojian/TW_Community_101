package com.community101.web;

import com.community101.core.Category;
import com.community101.core.Goods;
import com.community101.core.service.CategoryService;
import com.community101.core.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenbojian on 7/17/15.
 */
@RestController
@RequestMapping("/input-goods")
public class InputGoodsController {
    private GoodsService goodsService;
    private CategoryService categoryService;

    @Autowired
    public InputGoodsController(GoodsService goodsService, CategoryService categoryService) {
        this.goodsService = goodsService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = {"", "/"})
    public ModelAndView inputGoodsPage() {
        ModelAndView modelAndView = new ModelAndView("inputGoods");
        modelAndView.addObject("goods", new Goods());
        List<Category> categories = categoryService.listCategory();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView inputGoods(Goods goods, float floatPrice,
                                   MultipartFile pictureFile, HttpSession session) throws IOException {
        String realPath = session.getServletContext().getRealPath("/");
        String contextPath = session.getServletContext().getContextPath();
        goods.setPictureUrl(savePicture(pictureFile, realPath, contextPath));
        goods.setPrice((int) (floatPrice * 100));
        Category category = categoryService.findCategoryById(goods.getCategory().getId());
        goods.setCategory(category);
        goodsService.save(goods);
        return inputGoodsPage();
    }

    private String savePicture(MultipartFile pictureFile, String realPath, String contextPath) throws IOException {
        String fileName = pictureFile.getOriginalFilename();
        File picDir = new File(realPath + "/picture/");
        picDir.mkdirs();
        File file = new File(picDir.getPath() + "/" + fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(pictureFile.getBytes());
        fileOutputStream.close();
        return contextPath + "/picture/" + fileName;

    }
}
