package com.community101.core.DTO;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
public class GoodsDetailedDTO extends GoodsSimpleDTO {
    private String description;

    public GoodsDetailedDTO() {}

    public GoodsDetailedDTO(long id, String name, int price, String pic, String description) {
        super(id, name, price, pic);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
