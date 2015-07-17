package com.community101.core.DTO;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chenbojian on 7/17/15.
 */
public class InputGoodsDTO {
    private String name;
    private String description;
    private String price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public MultipartFile getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
    }
}
