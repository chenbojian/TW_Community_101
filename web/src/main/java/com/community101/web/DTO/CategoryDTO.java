package com.community101.web.DTO;


/**
 * Created by MiffyLiye on 16/07/2015.
 */
public class CategoryDTO {
    private long id;
    private String name;

    public CategoryDTO() {}

    public CategoryDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
