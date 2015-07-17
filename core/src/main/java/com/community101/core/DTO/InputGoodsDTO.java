package com.community101.core.DTO;

import com.community101.core.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by chenbojian on 7/17/15.
 */
public class InputGoodsDTO {
    private String name;
    private String description;
    private int price;
    private MultipartFile pictureFile;
    private String pictureUrl;
    private String uri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MultipartFile getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
    }


    public Goods toGoods() {
        Goods goods = new Goods();
        goods.setName(name);
        goods.setDescription(description);
        goods.setPictureUrl(uri);
        goods.setPrice(price);
        goods.setStatus("selling");
        return goods;

    }

    public void savePicture(String realPath, String contextPath) throws IOException {
        System.out.println("realPath = [" + realPath + "], contextPath = [" + contextPath + "]");
        String fileName = pictureFile.getOriginalFilename();
        File picDir = new File(realPath + "/picture/");
        picDir.mkdirs();
        File file = new File(picDir.getPath() + "/" + fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(pictureFile.getBytes());
        fileOutputStream.close();
        uri =contextPath + "/picture" + fileName;

    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
