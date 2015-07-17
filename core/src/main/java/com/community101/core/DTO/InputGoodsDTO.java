package com.community101.core.DTO;

import com.community101.core.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by chenbojian on 7/17/15.
 */
public class InputGoodsDTO {
    private String name;
    private String description;
    private int price;
    private MultipartFile pictureFile;

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
//        try {
//            File pic= new File("/home/chenbojian/testFileSave");
//            pictureFile.transferTo(new File(""));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Goods goods = new Goods();
        goods.setName(name);
        goods.setDescription(description);
        goods.setPictureUrl("myurl");
        goods.setPrice(price);
        goods.setStatus("selling");
        return goods;

    }
}
