package com.community101.admin.api;

import com.community101.core.Goods;
import com.community101.core.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenbojian on 7/31/15.
 */

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Goods> listAll() {
        return goodsService.listGoods();
    }

    @RequestMapping(value = "/picture/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadPicture(@RequestBody MultipartFile file, HttpSession session) throws IOException {
        String realPath = session.getServletContext().getRealPath("/");
        String contextPath = session.getServletContext().getContextPath();
        return savePicture(file, realPath, contextPath);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addGoods(@RequestBody Goods goods) {
        goodsService.save(goods);
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
